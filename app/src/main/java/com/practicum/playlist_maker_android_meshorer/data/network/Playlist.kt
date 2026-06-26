package com.practicum.playlist_maker_android_meshorer.data.network

data class Playlist(
    val id: Long,
    val name: String,
    val description: String,
    val tracks: List<Track>
)