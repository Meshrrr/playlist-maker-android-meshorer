package com.practicum.playlist_maker_android_meshorer


data class PlayerStatus(
    val isPlaying: Boolean = false,
    val progress: Float = 0f,
    val duration: Float = 0f
) {
    companion object {
        val Initial = PlayerStatus()
    }
}