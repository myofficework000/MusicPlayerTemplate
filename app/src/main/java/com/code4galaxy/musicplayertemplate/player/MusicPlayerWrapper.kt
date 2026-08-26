package com.code4galaxy.musicplayertemplate.player

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicPlayerWrapper @Inject constructor(
    private val exoPlayer: ExoPlayer
) {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition = _currentPosition.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration = _duration.asStateFlow()

    init {
        exoPlayer.addListener(
            object : Player.Listener {

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    _isPlaying.value = isPlaying
                }

                override fun onPlaybackStateChanged(playbackState: Int) {

                    if (playbackState == Player.STATE_READY) {
                        _duration.value = exoPlayer.duration
                    }

                    if (playbackState == Player.STATE_ENDED) {
                        _isPlaying.value = false
                    }
                }
            }
        )
    }

    fun play(url: String) {

        _currentPosition.value = 0L
        _duration.value = 0L

        val mediaItem = MediaItem.fromUri(
            Uri.parse(url)
        )

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun resume() {
        exoPlayer.play()
    }

    fun stop() {

        exoPlayer.stop()

        _currentPosition.value = 0L
        _duration.value = 0L
        _isPlaying.value = false
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    fun updatePosition() {
        _currentPosition.value =
            exoPlayer.currentPosition
    }

    fun release() {
        exoPlayer.release()
    }
}