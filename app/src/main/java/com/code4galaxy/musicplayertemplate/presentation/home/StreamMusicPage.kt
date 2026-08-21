package com.code4galaxy.musicplayertemplate.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.session.CommandButton
import com.code4galaxy.musicplayertemplate.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun StreamMusic(
    modifier: Modifier = Modifier,
) {
    val state = rememberSliderState(
    value = 20f,
    valueRange = 0f..100f
    )
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (boxImage, backArrow,nowPlaying,musicImage) = createRefs()
        val (songTitle, songArtist,slider,playSong) = createRefs()
        val (prevSong,nextSong)=createRefs()

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

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(backArrow) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }.padding(top = 28.dp, start = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_arrow_back_ios_24),
                contentDescription = "Back",
                tint = Color.White
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
            modifier = modifier
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
            state = state,
            colors = SliderDefaults.colors(
                thumbColor = Color.Yellow,
                activeTrackColor = Color.Yellow,
                inactiveTrackColor = Color.Gray
            )
        )
        Button(
            onClick = {},
            modifier = modifier.padding(
                top = 20.dp
            )
                .size(64.dp)
                .constrainAs(playSong) {
                    top.linkTo(slider.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
            )

        ) {
            Icon(
                painter = painterResource(R.drawable.img_1),
                contentDescription = "Play",
                tint = Color.Black,
                modifier = modifier.size(48.dp)
            )
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(
                top = 20.dp,
                start = 16.dp
            ).constrainAs(prevSong) {
                    top.linkTo(slider.bottom)
                    start.linkTo(parent.start)

                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )

        ) {
            Icon(
                painter = painterResource(R.drawable.img_2),
                contentDescription = "Play",
                modifier = Modifier.size(24.dp)
            )
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(
                top = 20.dp,
                end = 16.dp
            )
                .constrainAs(nextSong) {
                    top.linkTo(slider.bottom)
                    end.linkTo(parent.end)

                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )


        ) {
            Icon(
                painter = painterResource(R.drawable.img_3),
                contentDescription = "Play",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
