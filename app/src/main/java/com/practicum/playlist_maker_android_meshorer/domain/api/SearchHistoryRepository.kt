package com.practicum.playlist_maker_android_meshorer.domain.api

import com.practicum.playlist_maker_android_meshorer.data.dto.Word
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    suspend fun getHistoryRequests(): Flow<List<String>>

    fun addToHistory(word: String)

}