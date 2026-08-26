package com.code4galaxy.musicplayertemplate.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.code4galaxy.musicplayertemplate.R
import com.code4galaxy.musicplayertemplate.ui.theme.Purple_Dark
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun StreamMusic(
    modifier: Modifier = Modifier,
) {
    var currentPosition by remember { mutableStateOf(20f) }
    val duration = 180f
    var isPlaying by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (boxImage, backArrow,nowPlaying,musicImage) = createRefs()
        val (songTitle, songArtist,slider,playSong) = createRefs()
        val (prevSong,nextSong,durationText)=createRefs()

        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Music Image",
            alpha = 0.15f,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(boxImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )

        IconButton(
            onClick = {},
            modifier = Modifier
                .constrainAs(backArrow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(top = 28.dp, start = 10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_arrow_back_ios_24),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "Now Playing",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(nowPlaying){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.padding(top = 40.dp, start = 20.dp),
            color = Color.White

        )
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Music Image",
            contentScale = ContentScale.Fit,
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
                .constrainAs(musicImage) {
                    top.linkTo(nowPlaying.bottom, margin = 40.dp)
                    start.linkTo(parent.start, margin = 40.dp)
                    end.linkTo(parent.end, margin = 40.dp)
                }
        )
        Text(
            text = "Song Title",
            fontSize = 36.sp,
            color = Color.White,
            modifier=modifier.constrainAs(songTitle){
                top.linkTo(musicImage.bottom)
                start.linkTo(parent.start)
            }.padding(top=40.dp, start = 16.dp)
        )
        Text(
            text = "Artist Name",
            fontSize = 20.sp,
            color = Color.White,
            modifier=modifier.constrainAs(songArtist){
                top.linkTo(songTitle.bottom)
                start.linkTo(parent.start)
            }.padding(top=10.dp, start = 16.dp)
        )
        Slider(
            value = currentPosition,
            onValueChange = { currentPosition = it },
            valueRange = 0f..duration.coerceAtLeast(1f),
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .constrainAs(slider) {
                    top.linkTo(songArtist.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = SliderDefaults.colors(
                thumbColor = Purple_Dark,
                activeTrackColor = Purple_Dark,
                inactiveTrackColor = Color.Gray
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .constrainAs(durationText) {
                    top.linkTo(slider.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime(currentPosition),
                color = Color.White
            )

            Text(
                text = formatTime(duration),
                color = Color.White
            )
        }
        Button(
            onClick = {
                isPlaying = !isPlaying
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .size(64.dp)
                .constrainAs(playSong) {
                    top.linkTo(slider.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple_Dark
            )
        ) {
            Icon(
                imageVector = if (isPlaying) {
                    Icons.Default.Pause
                } else {
                    Icons.Default.PlayArrow
                },
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(
                top = 20.dp
            ).constrainAs(prevSong) {
                    top.linkTo(playSong.top)
                    start.linkTo(parent.start)
                end.linkTo(playSong.start)
                bottom.linkTo(playSong.bottom)

                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )

        ) {
            Icon(
                imageVector = Icons.Default.SkipPrevious,
                contentDescription = "Play Previous",
                modifier = Modifier.size(40.dp)
            )
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(
                top = 20.dp,

            )
                .constrainAs(nextSong) {
                    top.linkTo(playSong.top)
                    end.linkTo(parent.end)
                    start.linkTo(playSong.end)
                    bottom.linkTo(playSong.bottom)

                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )


        ) {
            Icon(
                imageVector = Icons.Default.SkipNext,
                contentDescription = "Play Next",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
fun formatTime(seconds: Float): String {
    val totalSeconds = seconds.toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds % 60

    return "%d:%02d".format(minutes, remainingSeconds)
}
