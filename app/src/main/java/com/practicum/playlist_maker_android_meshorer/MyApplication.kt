package com.practicum.playlist_maker_android_meshorer

import android.app.Application
import com.practicum.playlist_maker_android_meshorer.data.TracksRepositoryImpl
import com.practicum.playlist_maker_android_meshorer.data.network.RetrofitNetworkClient
import com.practicum.playlist_maker_android_meshorer.domain.api.TrackSearchInteractor
import com.practicum.playlist_maker_android_meshorer.domain.api.TracksRepository
import com.practicum.playlist_maker_android_meshorer.domain.impl.TrackSearchInteractorImpl

class MyApplication : Application() {

    private val repository: TracksRepository by lazy {
        TracksRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideTrackSearchInteractor(): TrackSearchInteractor {
        return TrackSearchInteractorImpl(repository)
    }
}