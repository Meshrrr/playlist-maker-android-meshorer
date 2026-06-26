package com.practicum.playlist_maker_android_meshorer.data.network

import com.practicum.playlist_maker_android_meshorer.data.dto.Word
import com.practicum.playlist_maker_android_meshorer.domain.api.SearchHistoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SearchHistoryRepositoryImpl(private val scope: CoroutineScope): SearchHistoryRepository {
    private val database = DataBaseMock(scope = scope)
    private val _historyFlow = MutableStateFlow<List<String>>(emptyList())

    override suspend fun getHistoryRequests(): Flow<List<String>> {
       return _historyFlow
    }

    override fun addToHistory(word: String) {
        database.addToHistory(Word(word = word))
        val current = database.getHistoryRequests().map { it.word }
        _historyFlow.value = current
    }
}

//разобраться почему история поиска не отображается
