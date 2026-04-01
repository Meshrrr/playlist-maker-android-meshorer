package com.practicum.playlist_maker_android_meshorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practicum.playlist_maker_android_meshorer.domain.api.TrackSearchInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
class TrackViewModel(
    private val trackId: String,
    private val tracksInteractor: TrackSearchInteractor,
    private val trackPlayer: TrackPlayer,
) : ViewModel() {
    private val _trackScreenState = MutableStateFlow<TrackScreenState>(TrackScreenState.Loading)
    val trackScreenState = _trackScreenState.asStateFlow()

    private val _playerStatusState = MutableStateFlow(PlayerStatus.Initial)
    val playerStatusState = _playerStatusState.asStateFlow()

    init {
        tracksInteractor.loadTrackData(
            trackId = trackId,
            onComplete = { trackModel ->
                // 1
                _trackScreenState.value = TrackScreenState.Content(trackModel)
            }
        )
    }

    private fun getCurrentPlayStatus(): PlayerStatus {
        return _playerStatusState.value // возвращаем текущее значение из StateFlow
    }

    fun play() {
        trackPlayer.play(
            trackId = trackId,
            statusObserver = object: TrackPlayer.StatusObserver {
                override fun onProgress(progress: Float) {
                    _playerStatusState.value = getCurrentPlayStatus().copy(progress = progress)
                }

                override fun onStop() {
                    _playerStatusState.value = getCurrentPlayStatus().copy(isPlaying = false)
                }

                override fun onPlay() {
                    _playerStatusState.value = getCurrentPlayStatus().copy(isPlaying = true)
                }

            })
    }

    fun pause() {
        trackPlayer.pause(trackId)
    }

    override fun onCleared() {
        trackPlayer.release(trackId)
    }

    companion object {
        fun getViewModelFactory(trackId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApp = this[APPLICATION_KEY] as MyApplication
                val interactor = myApp.provideTrackSearchInteractor()
                val trackPlayer = myApp.provideTrackPlayer()

                TrackViewModel(
                    trackId,
                    interactor,
                    trackPlayer
                )
            }
        }
    }
}