package com.example.mymusicapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailedViewActivity : AppCompatActivity() {
    
    private lateinit var textViewPlaylistDetails: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)
        
        textViewPlaylistDetails = findViewById(R.id.textViewPlaylistDetails)
        
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
}
