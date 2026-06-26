package com.practicum.playlist_maker_android_meshorer.data.network

import androidx.compose.runtime.mutableStateOf
import com.practicum.playlist_maker_android_meshorer.data.dto.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class DataBaseMock (val scope: CoroutineScope) {

    private val historyList = mutableListOf<String>()
    private val _historyUpdates = MutableSharedFlow<Unit>()


    fun getHistoryRequests(): List<Word> {
        return historyList.map { Word(it) }
    }

    fun notifyHistoryChanged() {
        scope.launch(Dispatchers.IO) {
            _historyUpdates.emit(Unit)
        }
    }

    fun addToHistory(word: Word) {
        historyList.add(word.word)
        notifyHistoryChanged()
    }

}