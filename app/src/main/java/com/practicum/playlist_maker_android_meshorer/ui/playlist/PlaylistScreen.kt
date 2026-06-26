package com.practicum.playlist_maker_android_meshorer.ui.playlist

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.playlist_maker_android_meshorer.R
import com.practicum.playlist_maker_android_meshorer.data.network.Playlist
import com.practicum.playlist_maker_android_meshorer.data.network.Track

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier,
                   playlistsViewModel: PlaylistViewModel,
                   navigateBack: () -> Unit = {},
                   navigateToPlaylist: (Long) -> Unit = {},
                   addNewPlayList: () -> Unit = {},
                   ) {

    val playlists by playlistsViewModel.playlists.collectAsState(emptyList())



    Box {
        Column(modifier=modifier
            .fillMaxSize()
            .background(Color.White)) {

            Row(modifier=modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White),
                verticalAlignment = Alignment.CenterVertically) {

                Button(onClick= { navigateBack() },
                    modifier=modifier
                        .padding(start = 12.dp)
                        .size(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(0.dp),
                    content = {
                        Image(modifier = Modifier.size(24.dp),
                            contentDescription = "Назад",
                            painter = painterResource(id = R.drawable.arrow_back)
                        )},
                )

                Text(text = stringResource(R.string.playlist_screen_text),
                    modifier=modifier.padding(start = 12.dp),
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.SansSerif)

            }

            LazyColumn {
                items(playlists.size) {index ->
                    PlaylistListItem(playlist = playlists[index]) {
                        navigateToPlaylist(playlists[index].id)
                    }
                    HorizontalDivider(thickness = 0.5.dp)
                }
            }
        }
        FloatingActionButton(modifier = Modifier.padding(32.dp).align(Alignment.BottomEnd),
            onClick = { addNewPlayList()}) {
            Image(painter = painterResource(R.drawable.add_playlist),
                modifier = Modifier.size(23.dp), contentDescription = null)

        }
    }
}

@Composable
fun PlaylistListItem(playlist: Playlist,
                     onClick: () -> Unit = {}) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(61.dp)
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically) {

        Image(painter = painterResource(R.drawable.playlist_icon),
            modifier = Modifier.size(45.dp).padding(start = 13.dp),
            contentDescription = playlist.name)

        Column(modifier = Modifier.weight(1f)) {
            Text("sssss", fontSize = 16.sp)
            Text("${playlist.tracks.size} треков", fontSize = 11.sp, color = Color.Gray)
        }

    }

}

//доделать экран создания плейлиста, экран трека, проверить поиск