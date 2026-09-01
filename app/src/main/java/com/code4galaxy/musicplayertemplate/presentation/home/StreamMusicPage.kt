package com.code4galaxy.musicplayertemplate.presentation.home

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.code4galaxy.musicplayertemplate.R
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.ui.MusicPlayerViewModel
import com.code4galaxy.musicplayertemplate.ui.theme.Purple_Dark




/**
 * Displays the music streaming screen for the provided track.
 * This composable observes the track UI state from [MusicViewModel] and
 * displays loading, error, or music player content based on the current state.
 * When [trackId] changes, the track details are requested from the ViewModel.
 * @param trackId Unique identifier of the track to be loaded and played.
 * @param modifier Modifier used to customize the layout of the screen.
 * @param trackViewModel ViewModel responsible for loading and exposing the selected track details.
 * @param playerViewModel ViewModel responsible for controlling music playback,
 * playback position, duration, play/pause, and seeking.
 */




@Composable
fun StreamMusic(
    trackId: String,
    modifier: Modifier = Modifier,
    trackViewModel: MusicViewModel = hiltViewModel(),
    playerViewModel: MusicPlayerViewModel = hiltViewModel()
) {
    val trackUiState by trackViewModel.trackUiState.collectAsState()

    when (val state = trackUiState) {

        UiState.Idle -> {}
        UiState.Loading -> {
            Text(
                text = "Loading...",
                color = Color.White,
                modifier = modifier
                    .fillMaxSize()
                    .background(brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF121212),
                            Color(0xFF1B1028),
                            Color(0xFF24123A)
                        )
                    ))
            )
        }

        is UiState.Error -> {
            Text(
                text = state.message,
                color = Color.White,
                modifier = modifier
                    .fillMaxSize()
                    .background(brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF121212),
                            Color(0xFF1B1028),
                            Color(0xFF24123A)
                        )
                    ))
            )
        }

        is UiState.Success -> {
            MusicPlayerContent(
                track = state.data,
                playerViewModel = playerViewModel,
                modifier = modifier
            )
        }
    }

    LaunchedEffect(trackId) {
        trackViewModel.getTrackDetails(trackId)
    }
}




/** Displays the music player UI for the provided [Track].
 * The screen shows the track artwork, title, artist name, playback progress, duration, and playback controls.
 * Playback state, current playback position, and duration are observed from  [MusicPlayerViewModel].
 * The user can seek through the track using the slider and can start, pause, or resume playback using the play/pause button.
 * @param track Track whose information and audio stream are displayed.
 * @param playerViewModel ViewModel responsible for controlling music playback and exposing the current playback state.
 * @param modifier Modifier used to customize the music player layout.
 */


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MusicPlayerContent(
    track: Track,
    playerViewModel: MusicPlayerViewModel,
    modifier: Modifier = Modifier
) {
    val isPlaying by playerViewModel.isPlaying.collectAsState()
    val currentPosition by playerViewModel.currentPosition.collectAsState()
    val duration by playerViewModel.duration.collectAsState()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF121212),
                    Color(0xFF1B1028),
                    Color(0xFF24123A)
                )
            ))
    ) {
        val (backArrow, nowPlaying, musicImage) = createRefs()
        val (songTitle, songArtist, slider, playSong) = createRefs()
        val (prevSong, nextSong, durationText) = createRefs()

        IconButton(
            onClick = {},
            modifier = Modifier
                .constrainAs(backArrow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(
                    top = 28.dp,
                    start = 10.dp
                )
        ) {
            Icon(
                painter = painterResource(
                    R.drawable.outline_arrow_back_ios_24
                ),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = "Now Playing",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .constrainAs(nowPlaying) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 40.dp, start = 20.dp)
        )

        GlideImage(
            model = track.artworkUrl,
            contentDescription = track.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(440.dp).padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .constrainAs(musicImage) {
                    top.linkTo(nowPlaying.bottom, margin = 40.dp)
                    start.linkTo(parent.start, margin = 40.dp)
                    end.linkTo(parent.end, margin = 40.dp)
                }
        )
        Text(
            text = track.title,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .constrainAs(songTitle) {
                    top.linkTo(musicImage.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 40.dp, start = 16.dp)
        )

        Text(
            text = track.artistName,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .constrainAs(songArtist) {
                    top.linkTo(songTitle.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 10.dp, start = 16.dp)
        )

        Slider(
            value = currentPosition.toFloat(),
            onValueChange = { playerViewModel.seekTo(it) },
            valueRange = 0f..duration.coerceAtLeast(1L).toFloat(),
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
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
                .padding(horizontal = 20.dp)
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
                if (isPlaying) {
                    playerViewModel.playPause()
                } else {
                    playerViewModel.play(track.streamUrl)
                }
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .size(64.dp)
                .constrainAs(playSong) {
                    top.linkTo(durationText.bottom)
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
                contentDescription = if (isPlaying) { "Pause" } else { "Play" },
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

        Button(
            onClick = {
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(prevSong) {
                    top.linkTo(playSong.top)
                    start.linkTo(parent.start)
                    end.linkTo(playSong.start)
                    bottom.linkTo(playSong.bottom)
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.SkipPrevious,
                contentDescription = "Previous song",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }

        Button(
            onClick = {
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(nextSong) {
                    top.linkTo(playSong.top)
                    start.linkTo(playSong.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(playSong.bottom)
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.SkipNext,
                contentDescription = "Next song",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

/** * Converts a playback duration from milliseconds into a formatted minutes-and-seconds string.
 *  @param ms Playback duration or position in milliseconds.
 *  @return The formatted playback time in `MM:SS` format.
 */

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}
