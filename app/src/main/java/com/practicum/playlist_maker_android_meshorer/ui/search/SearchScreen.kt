package com.practicum.playlist_maker_android_meshorer.ui.search


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.playlist_maker_android_meshorer.R
import com.practicum.playlist_maker_android_meshorer.data.network.Track
import com.practicum.playlist_maker_android_meshorer.ui.HistoryRequest


@Composable
fun SearchScreen(
    modifier: Modifier,
    navigateBack: () -> Unit,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.getViewModelFactory()),
    onClick: (Int?) -> Unit,

) {

    val screenState by viewModel.searchscreenstate.collectAsState()
    var historyList by remember { mutableStateOf<List<String>>(emptyList()) }
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current


    LaunchedEffect(text) {
        viewModel.updateQuery(text)
    }

    LaunchedEffect(screenState) {
        when (screenState) {
            is SearchState.Success -> {
                focusManager.clearFocus()
            }
            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        historyList = viewModel.getHistoryList()
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Row(modifier = Modifier.fillMaxWidth().height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Button(
                    onClick = {
                        navigateBack()
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.padding(start = 4.dp).size(48.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    content = {
                        Image(modifier = Modifier.size(24.dp),
                            contentDescription = "Назад",
                            painter = painterResource(id = R.drawable.arrow_back)
                        )},

                )

                Text(modifier = Modifier.padding(start = 12.dp),
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(id = R.string.searching))

            }
        )


        OutlinedTextField(
            value = text,
            onValueChange = {newText -> text = newText },
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp).fillMaxWidth().focusRequester(focusRequester).onFocusChanged {focusState -> isFocused = focusState.isFocused},
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(16.dp).clickable {
                    viewModel.search(text)
                })},
            placeholder = {

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
//                        Image(modifier = Modifier.size(16.dp),
//                            painter = painterResource(id = R.drawable.grey_loop),
//                            contentDescription = null,)
                        Text(
                            text = stringResource(id = R.string.searching),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 2.dp),
                            color = Color.Gray)
                        }
                )
                    },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Clear,
                    modifier = Modifier.size(16.dp).clickable {
                        text = ""
                        viewModel.clearSearch()
                    },
                    contentDescription = null,

                    )

            }
        )

        if (isFocused && text.isEmpty() && historyList.isNotEmpty()) {
            HistoryRequest(
                historyList = historyList,
                onClick = { word ->
                    text = word
                }
            )
        }

        when(screenState) {
            is SearchState.Initial -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Введите строку для поиска",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }

            is SearchState.Searching -> {
                Box(modifier = Modifier.fillMaxSize().background(Color.White),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is SearchState.Success -> {
                Box(modifier = Modifier.fillMaxSize().background(Color.White),
                    contentAlignment = Alignment.Center) {

                    val tracks = (screenState as SearchState.Success).foundList

                    if(tracks.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(),
                            ) {
                            Text("Ничего не найдено", fontSize = 18.sp)
                        }
                    } else {

                        LazyColumn(modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp)) {

                            items(tracks.size) {
                                index ->
                                    TrackListItem(track = tracks[index],
                                        onClick = {onClick(index)})
                                    Divider(thickness = 0.5.dp, color = Color.LightGray)

                            }
                        }
                    }
                }
            }

            is SearchState.Fail -> {
                val error = (screenState as SearchState.Fail).error
                Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
                    Text("Ошибка: $error", fontSize = 14.sp,
                        color = Color.Red)
                }
            }

        }
        }
    }


@Preview
@Composable
private fun SearchPreview() {
    SearchScreen(navigateBack = {}, modifier = Modifier, onClick = {})
}

@Composable
fun TrackListItem(track: Track, onClick: () -> Unit) {

    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = { onClick() }),
        verticalAlignment = Alignment.CenterVertically) {

        Image(modifier = Modifier.size(40.dp),
            contentDescription = "Трэк - ${track.trackName}",
            painter = painterResource(id=R.drawable.playlist_icon))


        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start) {
            Text(track.trackName,
                fontWeight = FontWeight.Bold)
            Text(track.artistName,
                color = Color.Gray)
        }

        Column(modifier = Modifier.weight(1f),
            ) {
            Text(text = track.trackTime, fontSize = 14.sp, color = Color.Gray)
        }

    }
}

@Preview
@Composable
private fun  TrackIconPreview() {
    Image(modifier = Modifier.size(40.dp),
        contentDescription = "Track Icon",
        painter = painterResource(id=R.drawable.playlist_icon))
}


@Preview
@Composable
private fun TracknamePreview() {
    Column {
        Text("Название трека", fontWeight = FontWeight.Bold)
        Text("Исполнитель", color = Color.Gray)
    }
}

@Preview
@Composable
private fun TimeTrackPreview() {
    Column {
        Text("2:34")
    }
}

@Preview(showSystemUi = true)
@Composable
private fun showListTrackPreview() {
    TrackListItem(Track(id=0, trackName = "Love me", artistName = "Drake", trackTime = "2:12", image="", isFavourite = false, playlistId = 0), onClick = {})
}