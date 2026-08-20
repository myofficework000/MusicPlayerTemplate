package com.code4galaxy.musicplayertemplate.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code4galaxy.musicplayertemplate.player.MusicPlayerWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    private val musicPlayerWrapper: MusicPlayerWrapper
) : ViewModel() {
    val isPlaying = musicPlayerWrapper.isPlaying
    val currentPosition = musicPlayerWrapper.currentPosition
    val duration = musicPlayerWrapper.duration

    init {
        viewModelScope.launch {
            while (true) {
                musicPlayerWrapper.updatePosition()
                delay(1000)
            }
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

    fun play(url: String) {
        musicPlayerWrapper.play(url)
    }
}

@Composable
fun MusicPlayerScreen(
    viewModel: MusicPlayerViewModel = hiltViewModel()
) {
    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()
    val duration by viewModel.duration.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Music Player",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Slider(
            value = currentPosition.toFloat(),
            onValueChange = { viewModel.seekTo(it) },
            valueRange = 0f..duration.toFloat().coerceAtLeast(1f),
            modifier = Modifier.fillMaxWidth()
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatTime(currentPosition))
            Text(text = formatTime(duration))
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = { viewModel.playPause() },
            modifier = Modifier.size(width = 120.dp, height = 50.dp)
        ) {
            Text(text = if (isPlaying) "Pause" else "Play")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { viewModel.play("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3") }
        ) {
            Text(text = "Play Sample Track")
        }
    }
}

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}
