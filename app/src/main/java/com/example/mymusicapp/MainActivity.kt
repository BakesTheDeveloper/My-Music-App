package com.example.mymusicapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private val playlist = mutableListOf<Song>()
    private lateinit var editTextTitle: EditText
    private lateinit var editTextArtist: EditText
    private lateinit var editTextRating: EditText
    private lateinit var buttonAddSong: Button
    private lateinit var buttonDetailedView: Button
    private lateinit var buttonExit: Button
    private lateinit var buttonDisplayPlaylist: Button
    private lateinit var buttonAverageRating: Button
    
    companion object {
        const val MAX_SONGS = 5
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize UI components
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextArtist = findViewById(R.id.editTextArtist)
        editTextRating = findViewById(R.id.editTextRating)
        buttonAddSong = findViewById(R.id.buttonAddSong)
        buttonDetailedView = findViewById(R.id.buttonDetailedView)
        buttonExit = findViewById(R.id.buttonExit)
        buttonDisplayPlaylist = findViewById(R.id.buttonDisplayPlaylist)
        buttonAverageRating = findViewById(R.id.buttonAverageRating)
        
        // Set up add song button
        buttonAddSong.setOnClickListener {
            addSongToPlaylist()
        }
        
        // Set up detailed view button
        buttonDetailedView.setOnClickListener {
            navigateToDetailedView()
        }
        
        // Set up display playlist button
        buttonDisplayPlaylist.setOnClickListener {
            displayPlaylistUsingLoop()
        }
        
        // Set up average rating button
        buttonAverageRating.setOnClickListener {
            calculateAndDisplayAverageRating()
        }
        
        // Set up exit button
        buttonExit.setOnClickListener {
            exitApp()
        }
    }
    
    private fun addSongToPlaylist() {
        // Check if playlist is full
        if (playlist.size >= MAX_SONGS) {
            Toast.makeText(this, "Playlist is full! Maximum $MAX_SONGS songs allowed.", Toast.LENGTH_SHORT).show()
            return
        }
        
        val title = editTextTitle.text.toString().trim()
        val artist = editTextArtist.text.toString().trim()
        val ratingText = editTextRating.text.toString().trim()
        
        // Validate input
        if (title.isEmpty() || artist.isEmpty() || ratingText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }
        
        try {
            val rating = ratingText.toDouble()
            if (rating < 0 || rating > 5) {
                Toast.makeText(this, "Rating must be between 0 and 5", Toast.LENGTH_SHORT).show()
                return
            }
            
            // Create and add song to playlist
            val newSong = Song(title, artist, rating)
            playlist.add(newSong)
            
            // Clear input fields
            editTextTitle.setText("")
            editTextArtist.setText("")
            editTextRating.setText("")
            
            Toast.makeText(this, "Song added! (${playlist.size}/$MAX_SONGS songs)", Toast.LENGTH_SHORT).show()
            
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid rating number", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun navigateToDetailedView() {
        val intent = Intent(this, DetailedViewActivity::class.java)
        
        // Convert playlist to string array for passing to intent
        val playlistData = ArrayList<String>()
        for (song in playlist) {
            playlistData.add(song.toString())
        }
        
        intent.putStringArrayListExtra("playlist_data", playlistData)
        startActivity(intent)
    }
    
    private fun exitApp() {
        finishAffinity() // Closes all activities and exits the app
    }
    
    private fun displayPlaylistUsingLoop() {
        if (playlist.isEmpty()) {
            Toast.makeText(this, "Playlist is empty! Add some songs first.", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Build playlist string using a loop
        val playlistBuilder = StringBuilder()
        playlistBuilder.append("Current Playlist:\n\n")
        
        // Loop through playlist to display each song
        for (i in playlist.indices) {
            val song = playlist[i]
            playlistBuilder.append("${i + 1}. ${song.title}\n")
            playlistBuilder.append("   Artist: ${song.artist}\n")
            playlistBuilder.append("   Rating: ${song.rating}/5\n\n")
        }
        
        playlistBuilder.append("Total Songs: ${playlist.size}/$MAX_SONGS")
        
        // Display playlist in a dialog
        AlertDialog.Builder(this)
            .setTitle("Playlist Display")
            .setMessage(playlistBuilder.toString())
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    
    private fun calculateAndDisplayAverageRating() {
        if (playlist.isEmpty()) {
            Toast.makeText(this, "Playlist is empty! Add some songs first.", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Calculate average rating using a loop
        var totalRating = 0.0
        var songCount = 0
        
        // Loop through playlist to sum all ratings
        for (song in playlist) {
            totalRating += song.rating
            songCount++
        }
        
        val averageRating = totalRating / songCount
        
        // Build detailed rating information
        val ratingBuilder = StringBuilder()
        ratingBuilder.append("Rating Analysis:\n\n")
        
        // Loop to show individual ratings
        for (i in playlist.indices) {
            val song = playlist[i]
            ratingBuilder.append("${i + 1}. ${song.title}: ${song.rating}/5\n")
        }
        
        ratingBuilder.append("\n")
        ratingBuilder.append("Total Songs: $songCount\n")
        ratingBuilder.append("Sum of Ratings: $totalRating\n")
        ratingBuilder.append("Average Rating: ${String.format("%.2f", averageRating)}/5")
        
        // Display average rating in a dialog
        AlertDialog.Builder(this)
            .setTitle("Average Song Rating")
            .setMessage(ratingBuilder.toString())
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
