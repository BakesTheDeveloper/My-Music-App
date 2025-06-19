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

## 📸 Screenshots & Code Walkthrough

### Main Screen Interface
![Main Screen](screenshots/main_screen.png)

The main screen features a clean, intuitive interface with the custom background image and color-coordinated buttons. Each UI element is carefully positioned for optimal user experience.

**Layout Structure (activity_main.xml):**
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:background="@drawable/mainactivity">
    
    <!-- App Title with semi-transparent background -->
    <TextView
        android:text="My Music App"
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="#FFFFFF"
        android:background="#80000000"
        android:padding="8dp" />
```

**Key UI Components:**
- Custom background image (mainactivity.jpg) provides visual appeal
- Semi-transparent overlays ensure text readability
- Input fields with light backgrounds for better contrast
- Consistent button styling with #d2c6ae color scheme

### Song Input Interface
![Song Input](screenshots/song_input.png)

The input section allows users to add songs with three key pieces of information: title, artist, and rating.

**Input Validation Code (MainActivity.kt):**
```kotlin
private fun addSongToPlaylist() {
    // Check if playlist is full using array bounds
    if (currentSongCount >= MAX_SONGS) {
        Toast.makeText(this, "Playlist is full! Maximum $MAX_SONGS songs allowed.", Toast.LENGTH_SHORT).show()
        return
    }
    
    val title = editTextTitle.text.toString().trim()
    val artist = editTextArtist.text.toString().trim()
    val ratingText = editTextRating.text.toString().trim()
    
    // Validate all fields are filled
    if (title.isEmpty() || artist.isEmpty() || ratingText.isEmpty()) {
        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        return
    }
    
    // Validate rating range
    val rating = ratingText.toDouble()
    if (rating < 0 || rating > 5) {
        Toast.makeText(this, "Rating must be between 0 and 5", Toast.LENGTH_SHORT).show()
        return
    }
}
```

### Array-Based Storage Implementation
![Playlist Display](screenshots/playlist_display.png)

The playlist display demonstrates the core array-based storage system with loop-driven data retrieval.

**Array Management Code:**
```kotlin
// Fixed-size array declaration
private val playlist = Array<Song?>(MAX_SONGS) { null }
private var currentSongCount = 0

// Finding empty slot using traditional loop
var emptyIndex = -1
for (i in 0 until MAX_SONGS) {
    if (playlist[i] == null) {
        emptyIndex = i
        break
    }
}

// Adding song to array
if (emptyIndex != -1) {
    val newSong = Song(title, artist, rating)
    playlist[emptyIndex] = newSong
    currentSongCount++
}
```

### Loop-Based Display Function
![Display Playlist Dialog](screenshots/display_dialog.png)

The display playlist feature showcases loop-based iteration through the array structure.

**Display Implementation:**
```kotlin
private fun displayPlaylistUsingLoop() {
    val playlistBuilder = StringBuilder()
    playlistBuilder.append("Current Playlist:\n\n")
    
    var displayCount = 1
    // Loop through array to display each non-null song
    for (i in 0 until MAX_SONGS) {
        if (playlist[i] != null) {
            val song = playlist[i]!!
            playlistBuilder.append("$displayCount. ${song.title}\n")
            playlistBuilder.append("   Artist: ${song.artist}\n")
            playlistBuilder.append("   Rating: ${song.rating}/5\n\n")
            displayCount++
        }
    }
    
    playlistBuilder.append("Total Songs: $currentSongCount/$MAX_SONGS")
}
```

### Mathematical Loop Calculations
![Average Rating Calculation](screenshots/average_rating.png)

The average rating feature demonstrates mathematical operations using loop-based calculations.

**Average Calculation Code:**
```kotlin
private fun calculateAndDisplayAverageRating() {
    // Calculate average rating using loops
    var totalRating = 0.0
    var songCount = 0
    
    // Loop through array to sum all ratings
    for (i in 0 until MAX_SONGS) {
        if (playlist[i] != null) {
            totalRating += playlist[i]!!.rating
            songCount++
        }
    }
    
    val averageRating = totalRating / songCount
    
    // Display individual ratings using another loop
    for (i in 0 until MAX_SONGS) {
        if (playlist[i] != null) {
            val song = playlist[i]!!
            ratingBuilder.append("${displayCount}. ${song.title}: ${song.rating}/5\n")
        }
    }
}
```

### Detailed View Screen
![Detailed View](screenshots/detailed_view.png)

The detailed view provides a comprehensive overview of the playlist with enhanced formatting and navigation options.

**DetailedViewActivity Layout:**
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:background="@drawable/mainactivity">
    
    <ScrollView
        android:background="#CC000000"
        android:padding="12dp">
        
        <TextView
            android:id="@+id/textViewPlaylistDetails"
            android:textColor="#FFFFFF"
            android:textSize="16sp" />
    </ScrollView>
    
    <Button
        android:id="@+id/buttonBackToMain"
        android:backgroundTint="#d2c6ae"
        android:text="Back to Main Screen" />
</LinearLayout>
```

**Navigation Implementation:**
```kotlin
private fun navigateToDetailedView() {
    val intent = Intent(this, DetailedViewActivity::class.java)
    
    // Convert array to ArrayList for intent passing
    val playlistData = ArrayList<String>()
    for (i in 0 until MAX_SONGS) {
        if (playlist[i] != null) {
            playlistData.add(playlist[i].toString())
        }
    }
    
    intent.putStringArrayListExtra("playlist_data", playlistData)
    startActivity(intent)
}
```

### Data Structure Visualization
![Song Data Structure](screenshots/song_structure.png)

**Song Data Class:**
```kotlin
data class Song(
    var title: String,
    var artist: String,
    var rating: Double
) {
    override fun toString(): String {
        return "$title by $artist (Rating: $rating/5)"
    }
}
```

This simple yet effective data structure encapsulates all song information while providing a clean string representation for display purposes.

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
- 🟤 All Buttons: Warm Beige (`#d2c6ae`)

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
