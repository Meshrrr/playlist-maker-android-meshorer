package com.practicum.playlist_maker_android_meshorer.ui.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.practicum.playlist_maker_android_meshorer.R

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {

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
                    text = stringResource(id = R.string.settings))

            }
        )
        Column(modifier = Modifier.padding(top = 80.dp).fillMaxWidth().height(244.dp)) {

            ButtonSample(
                button_description = stringResource(id = R.string.black_theme),
                painter = painterResource(R.drawable.control_theme),
                width_icon = 35,
                height_icon = 18
                ) { }

            ButtonSample(
                button_description = stringResource(id = R.string.share_app),
                painter = painterResource(R.drawable.share_icon),
                width_icon = 24,
                height_icon = 24
            ) {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Download PlaylistMaker!")
                shareIntent.setType("text/plain")
                context.startActivity(shareIntent)
            }

            ButtonSample(
                button_description = stringResource(id = R.string.support),
                painter = painterResource(R.drawable.support),
                width_icon = 24,
                height_icon = 24
            ) {
                val supportSendIntent = Intent(Intent.ACTION_SENDTO)
                supportSendIntent.data = Uri.parse("mailto:")
                supportSendIntent.putExtra(Intent.EXTRA_EMAIL,
                    arrayOf(context.getString(R.string.mail)))
                supportSendIntent.putExtra(Intent.EXTRA_SUBJECT,
                    "Сообщение разработчикам и разработчицам приложения Playlist Maker")
                supportSendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Спасибо разработчикам и разработчицам за крутое приложение!")
                supportSendIntent.setType("text/plain")
                context.startActivity(supportSendIntent)


            }

            ButtonSample(
                button_description = stringResource(id = R.string.agreement),
                painter = painterResource(R.drawable.arrow_button),
                width_icon = 18,
                height_icon = 14
            ) {
                val agreementIntent = Intent(Intent.ACTION_VIEW)
                agreementIntent.data = context.getString(R.string.url_agreement).toUri()
                context.startActivity(agreementIntent)
            }
        }
    }
}

@Composable
private fun ButtonSample(button_description: String,
                         painter: Painter,
                         width_icon: Int,
                         height_icon: Int,
                         onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(61.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Text(modifier = Modifier.padding(start = 16.dp).weight(1f),
                        text = button_description,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black)

                    Image(modifier = Modifier.padding(end = 20.dp).width(width_icon.dp).height(height_icon.dp),
                        painter = painter,
                        contentDescription = button_description)
                }
            )
        },
    )
}


@Preview()
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(navigateBack = {})
}