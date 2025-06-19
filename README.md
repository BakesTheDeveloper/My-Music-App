# B.L Music App

A modern Android music playlist management application built with Kotlin, featuring array-based data structures and loop-driven functionality.

## 📱 Features

### Core Functionality
- Add Songs to Playlist: Add up to 5 songs with title, artist, and rating (0-5 scale)
- Display Playlist: View all songs in your playlist using loop-based iteration
- Calculate Average Rating: Compute and display the average rating of all songs using mathematical loops
- Detailed View: Navigate to a comprehensive playlist overview screen
- Navigation: Seamless navigation between main screen and detailed view
- Exit Application: Clean app termination functionality

### Technical Highlights
- Array-Based Storage: Uses fixed-size arrays instead of dynamic collections for educational purposes
- Loop-Driven Operations: All data processing implemented using traditional for loops
- Manual Memory Management: Custom index tracking and null checking for array elements
- Pure Kotlin Implementation: Modern Android development with Kotlin language features

## 🏗️ Architecture

### Data Structure
```kotlin
// Fixed-size array with nullable elements
private val playlist = Array<Song?>(MAX_SONGS) { null }
private var currentSongCount = 0
```

### Core Components
- Song.kt: Data class representing individual songs
- MainActivity.kt: Main interface with playlist management
- DetailedViewActivity.kt: Comprehensive playlist view screen

## 🎨 User Interface

### Design Elements
- Custom Background: Features mainactivity.jpg as the app background
- Color-Coded Buttons: Each function has a distinct color for easy identification
- Responsive Layout: Optimized for various screen sizes
- Text Readability: Semi-transparent overlays ensure content visibility over background

### Button Color Scheme
- 🟣 Add Song: Purple (`#6200EA`)
- 🟢 Display Playlist: Green (`#4CAF50`)
- 🟠 Average Rating: Orange (`#FF9800`)
- 🟣 Detailed View: Purple (`#9C27B0`)
- 🔴 Exit App: Red (`#FF5722`)
- 🔵 Back to Main: Blue (`#2196F3`)

## 🔧 Technical Implementation

### Array Operations
```kotlin
// Finding empty slot using loop
var emptyIndex = -1
for (i in 0 until MAX_SONGS) {
    if (playlist[i] == null) {
        emptyIndex = i
        break
    }
}
```

### Loop-Based Calculations
```kotlin
// Average rating calculation
var totalRating = 0.0
for (i in 0 until MAX_SONGS) {
    if (playlist[i] != null) {
        totalRating += playlist[i]!!.rating
    }
}
val averageRating = totalRating / songCount
```

## 📋 Requirements

- Android SDK: Minimum API level 21 (Android 5.0)
- Kotlin: Version 1.8+
- Android Studio: Arctic Fox or newer
- Target SDK: API level 34

## 🚀 Installation

1. Clone the repository
   ```bash
   git clone https://github.com/BakesTheDeveloper/My-Music-App.git
   ```

2. Open in Android Studio
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. Build and Run
   - Connect an Android device or start an emulator
   - Click "Run" or press Ctrl+R (Cmd+R on Mac)

## 📖 Usage

### Adding Songs
1. Enter song title, artist name, and rating (0-5)
2. Tap "Add Song to Playlist"
3. Song is added to the array-based playlist

### Viewing Playlist
- Quick View: Tap "Display Playlist" for a popup overview
- Detailed View: Tap "View Detailed Playlist" for comprehensive information

### Rating Analysis
- Tap "Calculate Average Rating" to see mathematical analysis
- View individual ratings and calculated average

## 🎯 Educational Focus

This app demonstrates fundamental programming concepts:

- Array Management: Fixed-size arrays with manual indexing
- Loop Structures: Traditional for loops for data processing
- Null Safety: Proper handling of nullable array elements
- Memory Efficiency: Manual tracking of array utilization
- Algorithm Implementation: Custom search and calculation algorithms

## 🔄 Version History

- v1.0: Initial release with core functionality
- v1.1: Added array-based implementation
- v1.2: Enhanced UI with custom background
- v1.3: Backend complete, frontend optimized

## 👨‍💻 Developer

BakesTheDeveloper
- GitHub: [@BakesTheDeveloper](https://github.com/BakesTheDeveloper)
- Project: B.L Music App

## 📄 License

This project is developed for educational purposes and personal use.

## 🤝 Contributing

This is an educational project demonstrating array and loop concepts. Feel free to fork and experiment with different implementations!

---

*Built with ❤️ using Kotlin and Android Studio*
