package com.code4galaxy.musicplayertemplate.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.useCase.GetTrackDetailsUseCase
import com.code4galaxy.musicplayertemplate.player.MusicPlayerWrapper
import com.code4galaxy.musicplayertemplate.presentation.home.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    private val musicPlayerWrapper: MusicPlayerWrapper,
    private val getTrackDetailsUseCase: GetTrackDetailsUseCase
) : ViewModel() {

    val isPlaying = musicPlayerWrapper.isPlaying
    val currentPosition = musicPlayerWrapper.currentPosition
    val duration = musicPlayerWrapper.duration

    private val _trackState =
        MutableStateFlow<UiState<Track>>(UiState.Idle)

    val trackState: StateFlow<UiState<Track>> =
        _trackState.asStateFlow()

    private var currentTrackUrl: String? = null

    init {
        viewModelScope.launch {
            while (true) {
                musicPlayerWrapper.updatePosition()
                delay(1000)
            }
        }
    }

    fun loadTrack(trackId: String) {

        viewModelScope.launch {

            _trackState.value = UiState.Loading

            try {

                val track = getTrackDetailsUseCase(trackId)

                _trackState.value =
                    UiState.Success(track)

            } catch (e: Exception) {

                _trackState.value =
                    UiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }

    fun play(url: String) {

        if (currentTrackUrl == url) {

            musicPlayerWrapper.resume()

        } else {

            currentTrackUrl = url
            musicPlayerWrapper.play(url)
        }
    }

    fun playPause() {

        if (isPlaying.value) {
            musicPlayerWrapper.pause()
        } else {
            musicPlayerWrapper.resume()
        }
    }

    fun seekTo(position: Float) {
        musicPlayerWrapper.seekTo(position.toLong())
    }
}

@Composable
fun MusicPlayerScreen(
    trackId: String,
    onBackClick: () -> Unit,
    viewModel: MusicPlayerViewModel = hiltViewModel()
) {

    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()
    val duration by viewModel.duration.collectAsState()
    val trackState by viewModel.trackState.collectAsState()

    LaunchedEffect(trackId) {
        viewModel.loadTrack(trackId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF121212),
                        Color(0xFF1B1028),
                        Color(0xFF24123A)
                    )
                )
            )
    ) {

        when (val state = trackState) {

            UiState.Idle -> {
            }

            UiState.Loading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Loading track...",
                        color = Color.White
                    )
                }
            }

            is UiState.Success -> {

                val track = state.data

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            onClick = onBackClick
                        ) {

                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Text(
                            text = "Now Playing",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    AsyncImage(
                        model = track.artworkUrl,
                        contentDescription = track.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp)
                            .clip(
                                RoundedCornerShape(24.dp)
                            )
                    )

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    Text(
                        text = track.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = track.artistName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.LightGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Slider(
                        value = currentPosition
                            .toFloat()
                            .coerceIn(
                                0f,
                                duration.toFloat().coerceAtLeast(1f)
                            ),
                        onValueChange = {
                            viewModel.seekTo(it)
                        },
                        valueRange = 0f..duration
                            .toFloat()
                            .coerceAtLeast(1f),
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFBB86FC),
                            activeTrackColor = Color(0xFFBB86FC),
                            inactiveTrackColor = Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = formatTime(currentPosition),
                            color = Color.LightGray
                        )

                        Text(
                            text = formatTime(duration),
                            color = Color.LightGray
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            onClick = {
                                // Previous track can be added later
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.SkipPrevious,
                                contentDescription = "Previous",
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        IconButton(
                            onClick = {

                                if (isPlaying) {

                                    viewModel.playPause()

                                } else {

                                    viewModel.play(
                                        track.streamUrl
                                    )
                                }
                            },
                            modifier = Modifier
                                .size(76.dp)
                                .clip(CircleShape)
                                .background(
                                    Color(0xFFBB86FC)
                                )
                        ) {

                            Icon(
                                imageVector =
                                    if (isPlaying) {
                                        Icons.Default.Pause
                                    } else {
                                        Icons.Default.PlayArrow
                                    },
                                contentDescription =
                                    if (isPlaying) {
                                        "Pause"
                                    } else {
                                        "Play"
                                    },
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                // Next track can be added later
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.SkipNext,
                                contentDescription = "Next",
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }
            }

            is UiState.Error -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = state.message,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

private fun formatTime(ms: Long): String {

    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    return String.format(
        "%02d:%02d",
        minutes,
        seconds
    )
}