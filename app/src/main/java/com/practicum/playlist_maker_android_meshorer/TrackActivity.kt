package com.practicum.playlist_maker_android_meshorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage



class TrackActivity : ComponentActivity() {
    private val viewModel by viewModels<TrackViewModel> { TrackViewModel.getViewModelFactory("123") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrackScreen(viewModel)
        }
    }
}

@Composable
fun TrackScreen(viewModel: TrackViewModel) {
    //
    val screenState by viewModel.trackScreenState.collectAsState()
    val state = screenState


    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        when (state) { // 2
            is TrackScreenState.Content -> {
                Column {
                    AsyncImage(
                        model = state.trackModel.pictureUrl,
                        contentDescription = null
                    )
                    Text(state.trackModel.author)
                    Text(state.trackModel.name)
                }
            }

            is TrackScreenState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}


//@Composable
//@OptIn(ExperimentalMaterial3Api::class)
//fun TrackScreenContent(viewModel: TrackViewModel, screenState: TrackScreenState) {
//    val playerStatus by viewModel.playerStatusState.collectAsState()
//
//    Column {
//        AsyncImage(
//            model = screenState.trackModel.pictureUrl,
//            contentDescription = null
//        )
//        Text(screenState.trackModel.author)
//        Text(screenState.trackModel.name)
//    }
//
//}
