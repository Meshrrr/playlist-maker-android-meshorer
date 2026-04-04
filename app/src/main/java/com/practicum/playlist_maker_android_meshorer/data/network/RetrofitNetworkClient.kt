package com.practicum.playlist_maker_android_meshorer.data.network

import com.practicum.playlist_maker_android_meshorer.creator.Storage
import com.practicum.playlist_maker_android_meshorer.domain.api.NetworkClient
import com.practicum.playlist_maker_android_meshorer.data.dto.TracksSearchResponse
import com.practicum.playlist_maker_android_meshorer.data.dto.TracksSearchRequest


class RetrofitNetworkClient(private val storage: Storage) : NetworkClient {

    override fun doRequest(request: Any): TracksSearchResponse {
        val searchList = storage.search((request as TracksSearchRequest).expression)
        return TracksSearchResponse(searchList).apply { resultCode = 200 }
    }
}
