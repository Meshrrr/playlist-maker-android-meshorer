package com.practicum.playlist_maker_android_meshorer.domain.api

import com.practicum.playlist_maker_android_meshorer.data.network.Track

interface TracksRepository {
    suspend fun getAllTracks(): List<Track>
    suspend fun searchTracks(expression: String): List<Track>
}