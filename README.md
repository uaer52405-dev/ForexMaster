# ForexMaster

A native Android trading courses app built with **Kotlin** and **Jetpack Compose Material3**.

## Features

- **Home Screen** - Landing page with hero section, stats, and feature highlights
- **Course Catalog** - Browse and filter courses by level (Beginner, Intermediate, Advanced, Expert)
- **Course Detail** - Full course information with topics, instructor, and enrollment
- **Login Screen** - Email and password authentication with validation
- **White and Blue Theme** - Clean Material3 design with consistent color scheme
- **Text Selection Disabled** - Across all screens

## Tech Stack

- Kotlin
- Jetpack Compose
- Material3 Design
- Navigation Compose
- Minimum SDK 26 (Android 8.0)
- Target SDK 35

## Building

```bash
./gradlew assembleDebug
```

The debug APK will be at `app/build/outputs/apk/debug/app-debug.apk`.

## CI/CD

GitHub Actions builds the project on every push to main and uploads the debug APK as a downloadable artifact.
