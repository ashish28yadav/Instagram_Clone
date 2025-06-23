# InstagramClone

A simple Instagram-like Android application built with Kotlin and Firebase. This project demonstrates user authentication, profile management, and a basic home/dashboard/notifications navigation structure.

---

## Features
- User registration and login (with Firebase Authentication)
- Profile image upload during sign-up
- Home, Dashboard, and Notifications navigation (BottomNavigationView)
- Splash screen
- User data stored in Firebase Firestore

---

## Screenshots

<!-- Replace the image links below with your own screenshots -->

### Splash Screen
![Screenshot 2025-06-23 154315](https://github.com/user-attachments/assets/f674927c-45b9-4625-9625-2d6d38fc3d87)

### Login Screen
![Login Screen](screenshots/login.png)
![Screenshot 2025-06-23 154411](https://github.com/user-attachments/assets/3038ad9f-a7c4-42ff-8d49-6f158a1144af)


### Sign Up Screen
![Sign Up Screen](screenshots/signup.png)
![Screenshot 2025-06-23 154345](https://github.com/user-attachments/assets/a5dab58c-f928-4512-a7a7-c7b248557fe9)


### Home Screen
![Home Screen](screenshots/home.png)
![home screen](https://github.com/user-attachments/assets/25a796c1-0ba7-49d5-8d01-795c10b84685)




---

## Getting Started

### Prerequisites
- Android Studio (latest version recommended)
- Android device or emulator
- Firebase project (for Authentication and Firestore)

### Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/InstagramClone.git
   cd InstagramClone
   ```
2. **Open in Android Studio:**
   - Open Android Studio and select `Open an existing project`.
   - Navigate to the cloned folder and open it.
3. **Configure Firebase:**
   - Add your `google-services.json` file to `app/` (already present, but replace with your own if needed).
   - Make sure Firebase Authentication and Firestore are enabled in your Firebase console.
4. **Build and Run:**
   - Connect your device or start an emulator.
   - Click `Run` in Android Studio.

---

## Technologies Used
- Kotlin
- Android Jetpack (Navigation, ViewModel, LiveData)
- Firebase Authentication
- Firebase Firestore
- Material Components

---

## Project Structure
- `app/src/main/java/com/example/instagramclone/` - Main activities and fragments
- `app/src/main/res/layout/` - UI layouts
- `app/src/main/res/drawable/` - App icons and images

---

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## License
[MIT](LICENSE) (or specify your license here)
