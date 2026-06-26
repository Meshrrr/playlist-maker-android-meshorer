package com.practicum.playlist_maker_android_meshorer.data.network

import com.practicum.playlist_maker_android_meshorer.domain.api.NetworkClient
import com.practicum.playlist_maker_android_meshorer.data.dto.TracksSearchResponse
import com.practicum.playlist_maker_android_meshorer.data.dto.TracksSearchRequest
import com.practicum.playlist_maker_android_meshorer.domain.api.TracksRepository
import kotlinx.coroutines.delay

class TracksRepositoryImpl(private val networkClient: NetworkClient) : TracksRepository {
    override suspend fun getAllTracks(): List<Track> {
        delay(1000)
        return emptyList()
        }
    override suspend fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        delay(1000) // Эммулируем задержку ответа
        return if (response.resultCode == 200) { // успешный запрос
            (response as TracksSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d".format(minutes) + ":" + "%02d".format(seconds - minutes * 60)
                Track(
                    id = 0,
                    trackName = it.trackName,
                    artistName = it.artistName,
                    trackTime = trackTime,
                    image = "",
                    isFavourite = false,
                    playlistId = -1
                ) }
        } else {
            emptyList()
        }
    }
}