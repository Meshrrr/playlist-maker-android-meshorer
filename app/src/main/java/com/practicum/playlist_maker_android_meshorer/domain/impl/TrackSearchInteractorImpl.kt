package com.practicum.playlist_maker_android_meshorer.domain.impl

import com.practicum.playlist_maker_android_meshorer.data.dto.Track

import com.practicum.playlist_maker_android_meshorer.domain.api.TrackSearchInteractor
import com.practicum.playlist_maker_android_meshorer.domain.api.TracksRepository

class TrackSearchInteractorImpl(private val repository: TracksRepository) : TrackSearchInteractor {

    override fun searchTracks(expression: String): List<Track> {
        return repository.searchTracks(expression)
    }
}