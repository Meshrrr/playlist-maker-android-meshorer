package com.practicum.playlist_maker_android_meshorer.domain.api

import com.practicum.playlist_maker_android_meshorer.data.dto.Track

interface TrackSearchInteractor {
    fun searchTracks(expression: String): List<Track>
}