package com.practicum.playlist_maker_android_meshorer.data

import com.practicum.playlist_maker_android_meshorer.data.dto.Track
import com.practicum.playlist_maker_android_meshorer.data.dto.TracksSearchRequest
import com.practicum.playlist_maker_android_meshorer.data.dto.TrackSearchResponse
import com.practicum.playlist_maker_android_meshorer.domain.api.TracksRepository

class TracksRepositoryImpl(private val networkClient: NetworkClient) : TracksRepository {

    override fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        if (response.resultCode == 200) { // успешный запрос
            return (response as TrackSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d".format(minutes) + ":" + "%02d".format(seconds - minutes * 60)
                Track(it.trackName, it.artistName, trackTime)
            }
        } else {
            return emptyList()
        }
    }
}