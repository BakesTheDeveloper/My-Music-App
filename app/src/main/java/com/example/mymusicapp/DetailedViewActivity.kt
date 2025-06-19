package com.example.mymusicapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailedViewActivity : AppCompatActivity() {
    
    private lateinit var textViewPlaylistDetails: TextView
    private lateinit var buttonBackToMain: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)
        
        textViewPlaylistDetails = findViewById(R.id.textViewPlaylistDetails)
        buttonBackToMain = findViewById(R.id.buttonBackToMain)
        
        // Set up back to main button
        buttonBackToMain.setOnClickListener {
            navigateToMainScreen()
        }
        
        // Get playlist data from intent
        val playlistData = intent.getStringArrayListExtra("playlist_data")
        
        if (playlistData != null && playlistData.isNotEmpty()) {
            displayPlaylistDetails(playlistData)
        } else {
            textViewPlaylistDetails.text = "No songs in playlist yet."
        }
    }
    
    private fun displayPlaylistDetails(playlistData: ArrayList<String>) {
        val detailsBuilder = StringBuilder()
        detailsBuilder.append("Playlist Details:\n\n")
        
        for (i in playlistData.indices) {
            detailsBuilder.append("${i + 1}. ${playlistData[i]}\n\n")
        }
        
        detailsBuilder.append("Total Songs: ${playlistData.size}")
        
        textViewPlaylistDetails.text = detailsBuilder.toString()
    }
    
    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish() // Close current activity
    }
}
