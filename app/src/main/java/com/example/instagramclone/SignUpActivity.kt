package com.example.instagramclone

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramclone.databinding.ActivitySignUpBinding
import com.example.instagramclone.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private var imageUri: Uri? = null
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Click listener to pick an image
        binding.profileImage.setOnClickListener {
            selectImage()
        }

        binding.signupBtn.setOnClickListener {
            val name = binding.nameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        val userId = auth.currentUser!!.uid
                        var imagePath = ""

                        if (imageUri != null) {
                            imagePath = saveImageLocally(imageUri!!, userId)
                        }

                        val user = User(imagePath, name, email, password)

                        db.collection("users").document(userId).set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "User Added Successfully", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, HomeActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { error ->
                                Toast.makeText(this, "Error Adding User: ${error.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Sign Up Failed: ${result.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Redirect to login screen
        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            binding.profileImage.setImageURI(imageUri)  // Set image preview
        }
    }

    private fun saveImageLocally(uri: Uri, userId: String): String {
        return try {
            val file = File(filesDir, "$userId.jpg")
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)

            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()

            file.absolutePath  // Return the saved image path
        } catch (e: Exception) {
            Toast.makeText(this, "Image save failed", Toast.LENGTH_SHORT).show()
            ""
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}
