package com.practicum.playlist_maker_android_meshorer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun HistoryRequest(historyList: List<String>,
                   onClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().heightIn(max = 200.dp),

    ) {
        items(historyList.size) { index ->
            Row(modifier = Modifier.fillMaxWidth()
                .clickable{onClick(historyList[index])}.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp))

                    Spacer(modifier = Modifier.size(20.dp))

                    Text(text = historyList[index])


            }

            if (historyList[index] != historyList.last()) {
                HorizontalDivider(thickness = 0.5.dp)
            }
        }
    }
}

@Preview
@Composable
fun previewHistory(){
    HistoryRequest(historyList = listOf("beatles", "zhuk", "alegrova anna"), onClick = {})
}