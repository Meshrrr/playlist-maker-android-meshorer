package com.practicum.playlist_maker_android_meshorer.data.network

data class Track(
    val id: Long,
    val trackName: String,
    val artistName: String,
    val trackTime: String,
    val image: String,
    val isFavorite: Boolean,
    var playlistId: Long
)