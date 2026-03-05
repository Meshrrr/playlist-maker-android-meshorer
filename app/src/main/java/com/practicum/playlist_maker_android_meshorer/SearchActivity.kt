package com.practicum.playlist_maker_android_meshorer

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

class SearchActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent { SearchScreen() }
    }
}

@Composable
fun SearchScreen() {
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
                        val homeIntent = Intent(context, MainActivity::class.java)
                        context.startActivity(homeIntent)
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

        Button(onClick = {},
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(vertical = 64.dp, horizontal = 16.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            content = {
                Image(modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.grey_loop),
                    contentDescription = null,)

                Text(modifier = Modifier.weight(1f).padding(start = 8.dp),
                    color = Color.Gray,
                    text = stringResource(id = R.string.searching))
            })

    }
}

@Preview
@Composable
private fun SearchPreview() {
    SearchScreen()
}