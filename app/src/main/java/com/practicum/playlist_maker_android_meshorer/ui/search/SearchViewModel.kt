package com.practicum.playlist_maker_android_meshorer.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_meshorer.creator.Storage
import com.practicum.playlist_maker_android_meshorer.data.network.RetrofitNetworkClient
import com.practicum.playlist_maker_android_meshorer.data.network.TracksRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okio.IOException

class SearchViewModel(private val tracksRepository: TracksRepositoryImpl): ViewModel() {
    val _searchscreenstate = MutableStateFlow<SearchState>(SearchState.Initial)

    val searchscreenstate = _searchscreenstate.asStateFlow()

    fun search(whatsearch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchscreenstate.value = SearchState.Searching
                val list = tracksRepository.searchTracks(whatsearch)
                _searchscreenstate.value = SearchState.Success(list)
            } catch (e: IOException) {
                _searchscreenstate.value = SearchState.Fail(e.message.toString())
            }
        }
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SearchViewModel(TracksRepositoryImpl(RetrofitNetworkClient(Storage())))
                    as T
                }
            }
    }
}