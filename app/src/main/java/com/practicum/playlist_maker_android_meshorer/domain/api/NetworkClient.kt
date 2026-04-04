package com.practicum.playlist_maker_android_meshorer.domain.api

import com.practicum.playlist_maker_android_meshorer.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}