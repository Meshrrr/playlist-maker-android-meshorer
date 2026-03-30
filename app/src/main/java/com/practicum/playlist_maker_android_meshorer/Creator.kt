package com.practicum.playlist_maker_android_meshorer

import com.practicum.playlist_maker_android_meshorer.data.TracksRepositoryImpl
import com.practicum.playlist_maker_android_meshorer.data.network.RetrofitNetworkClient
import com.practicum.playlist_maker_android_meshorer.domain.api.TrackSearchInteractor
import com.practicum.playlist_maker_android_meshorer.domain.api.TracksRepository
import com.practicum.playlist_maker_android_meshorer.domain.impl.TrackSearchInteractorImpl

object Creator {
    private fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideTrackSearchInteractor(): TrackSearchInteractor {
        return TrackSearchInteractorImpl(getTracksRepository())
    }
}