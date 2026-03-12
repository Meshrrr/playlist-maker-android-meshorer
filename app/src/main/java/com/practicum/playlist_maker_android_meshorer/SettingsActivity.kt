package com.practicum.playlist_maker_android_meshorer

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SettingsActivity:  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SettingsScreen() }
    }
}

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
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
                    text = stringResource(id = R.string.settings))

            }
        )

        Row(modifier = Modifier.fillMaxWidth().padding(top = 80.dp).height(61.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(modifier = Modifier.padding(start = 16.dp).weight(1f),
                    text = stringResource(id = R.string.black_theme),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.bodyMedium)

                Image(modifier = Modifier.padding(end = 18.dp).width(35.dp).height(18.dp),
                    painter = painterResource(id = R.drawable.control_theme),
                    contentDescription = null)
            })

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 141.dp).height(61.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(modifier = Modifier.padding(start = 16.dp).weight(1f),
                    text = stringResource(id = R.string.share_app),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.bodyMedium)

                Image(modifier = Modifier.padding(end = 12.dp).size(24.dp),
                    painter = painterResource(id = R.drawable.share_icon),
                    contentDescription = "Поделиться")

            }
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 202.dp).height(61.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(modifier = Modifier.padding(start = 16.dp).weight(1f),
                    text = stringResource(id = R.string.support),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.bodyMedium)

                Image(modifier = Modifier.padding(end = 12.dp).size(24.dp),
                    painter = painterResource(id = R.drawable.support),
                    contentDescription = (R.string.support.toString()))

            }
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 263.dp).height(61.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(modifier = Modifier.padding(start = 16.dp).weight(1f),
                    text = stringResource(id = R.string.agreement),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.bodyMedium)

                Image(modifier = Modifier.padding(end = 20.dp).width(8.dp).height(14.dp),
                    painter = painterResource(id = R.drawable.arrow_button),
                    contentDescription = (R.string.support.toString()))

            }
        )
    }
}

@Composable
private fun ButtonSample() {

}


@Preview()
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}