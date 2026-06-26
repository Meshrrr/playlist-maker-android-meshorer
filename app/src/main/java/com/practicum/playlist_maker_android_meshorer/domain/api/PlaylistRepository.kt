package com.practicum.playlist_maker_android_meshorer.domain.api

import com.practicum.playlist_maker_android_meshorer.data.network.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.descriptors.SerialDescriptor

interface PlaylistsRepository {

    fun getPlaylist(playlistId: Long): Flow<Playlist?>

    fun getAllPlaylists(): Flow<List<Playlist>>

    suspend fun addNewPlaylist(name: String, description: String)

    suspend fun deletePlaylistById(id: Long)

}