package com.example.mymusicapp

data class Song(
    var title: String,
    var artist: String,
    var rating: Double
) {
    override fun toString(): String {
        return "$title by $artist (Rating: $rating/5)"
    }
}
