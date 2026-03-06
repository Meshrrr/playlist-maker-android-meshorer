package com.practicum.playlist_maker_android_meshorer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_meshorer.ui.theme.PlaylistmakerandroidmeshorerTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Main_screen() }
    }
}

@Composable
fun Main_screen() {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize().background(Color(55, 114, 231)))
    {
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(start=16.dp, top = 14.dp,),

            text = stringResource(id = R.string.main_name),
           style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontSize = 22.sp)


        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .background(color = Color.White,
                shape = RoundedCornerShape(16.dp)),

            contentAlignment = Alignment.TopCenter,
        ) {
            Column(modifier = Modifier
                .height(264.dp).width(328.dp)
                .padding( top = 8.dp))
            {
                ButtonFields(button_description = stringResource(R.string.search_info),
                    painter = painterResource(id = R.drawable.loop_icon),
                    )
                {
                    val searchIntent = Intent(context, SearchActivity::class.java)
                    context.startActivity(searchIntent)
                }

                ButtonFields(button_description = stringResource(R.string.playlist_info),
                    painter = painterResource(id = R.drawable.playlist_icon))
                {

                }

                ButtonFields(button_description = stringResource(R.string.featured_info),
                    painter = painterResource(id = R.drawable.like_icon))
                {

                }

                ButtonFields(button_description = stringResource(R.string.settings_info),
                    painter = painterResource(id = R.drawable.settings_icon))
                {
                    val settingsIntent = Intent(context, SettingsActivity::class.java)
                    context.startActivity(settingsIntent)
                }
            }
        }
    }
}

@Composable
private fun ButtonFields(button_description: String, painter: Painter, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp),

        colors = ButtonDefaults.buttonColors(Color.White),
        onClick = onClick,
        content = {
            Row(Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {

                Image(modifier = Modifier.size(19.dp), painter = painter,
                    contentDescription = button_description)

                Text(text = button_description,
                    modifier = Modifier.padding(start =8.dp, top = 1.dp).weight(1f),
                    fontSize = 22.sp,
                    color = Color.Black)

                Image(modifier = Modifier.width(8.dp).height(14.dp),
                    contentDescription = button_description,
                    painter = painterResource(id = R.drawable.arrow_button))
            }
        }
    )
}


@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    Main_screen()

}


