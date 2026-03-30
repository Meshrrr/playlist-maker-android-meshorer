package com.practicum.playlist_maker_android_meshorer.data.network

import com.practicum.playlist_maker_android_meshorer.data.NetworkClient
import com.practicum.playlist_maker_android_meshorer.data.dto.BaseResponse
import com.practicum.playlist_maker_android_meshorer.data.dto.TrackSearchResponse
import kotlin.collections.listOf


class RetrofitNetworkClient : NetworkClient {

    override fun doRequest(dto: Any): BaseResponse {
        return TrackSearchResponse(listOf())
    }
}
