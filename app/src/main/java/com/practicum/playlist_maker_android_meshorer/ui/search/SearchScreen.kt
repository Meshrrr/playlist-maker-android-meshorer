package com.practicum.playlist_maker_android_meshorer.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_meshorer.R


@Composable
fun SearchScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    Box(modifier = Modifier
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

        var textField by remember { mutableStateOf("") }

        OutlinedTextField(
            value = textField,
            onValueChange = { textField = it },
            modifier = Modifier.padding(vertical = 64.dp, horizontal = 16.dp).fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Image(modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.grey_loop),
                            contentDescription = null,)
                        Text(
                            text = stringResource(id = R.string.searching),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp),
                            color = Color.Gray)
                        }
                )
                    },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Clear,
                    modifier = Modifier.size(16.dp).clickable {
                        textField = ""
                    },
                    contentDescription = null,

                    )

            }
        )
    }
}

@Preview
@Composable
private fun SearchPreview() {
    SearchScreen(navigateBack = {})
}

