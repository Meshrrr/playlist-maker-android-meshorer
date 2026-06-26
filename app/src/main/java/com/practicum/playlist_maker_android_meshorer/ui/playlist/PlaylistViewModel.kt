package com.practicum.playlist_maker_android_meshorer.ui.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_meshorer.data.network.Playlist
import com.practicum.playlist_maker_android_meshorer.data.network.PlaylistRepositoryImpl
import com.practicum.playlist_maker_android_meshorer.data.network.Track
import com.practicum.playlist_maker_android_meshorer.data.network.TracksRepositoryImpl
import com.practicum.playlist_maker_android_meshorer.domain.api.PlaylistsRepository
import com.practicum.playlist_maker_android_meshorer.domain.api.TracksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class PlaylistViewModel(): ViewModel() {

    private val playlistRepository: PlaylistsRepository = PlaylistRepositoryImpl(scope = viewModelScope)
    private val tracksRepository: TracksRepository = TracksRepositoryImpl(scope = viewModelScope)

    val playlists: Flow<List<Playlist>> = flow {
        val collectedPlaylists = mutableListOf<Playlist>()
        playlistRepository.getAllPlaylists().collect { playlist ->
            collectedPlaylists.addAll(playlist)
            emit(collectedPlaylists.toList())
        }
    }

    val favoriteList: Flow<List<Track>> = tracksRepository.getFavoriteTracks()

    fun createNewPlaylist(name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playlistRepository.addNewPlaylist(name = name, description = description)
        }
    }

    suspend fun insertTrackToPlaylist(track: Track, playlistId: Long) {
        tracksRepository.insertTrackToPlaylist(track = track, playlistId = playlistId)
    }

    suspend fun toggleFavorite(track: Track, isFavorite: Boolean) {
        tracksRepository.updateTrackFavoriteStatus(track = track, isFavorite = isFavorite)
    }

    suspend fun deleteTrackFromPlaylist(track: Track) {
        tracksRepository.deleteTrackFromPlaylist(track = track)
    }

    suspend fun deletePlaylistById(id: Long) {
        playlistRepository.deletePlaylistById(id = id)
    }
}