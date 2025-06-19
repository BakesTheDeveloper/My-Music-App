package com.example.mymusicapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private val playlist = mutableListOf<Song>()
    private lateinit var editTextTitle: EditText
    private lateinit var editTextArtist: EditText
    private lateinit var editTextRating: EditText
    private lateinit var buttonAddSong: Button
    
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
        
        // Set up add song button
        buttonAddSong.setOnClickListener {
            addSongToPlaylist()
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
}
