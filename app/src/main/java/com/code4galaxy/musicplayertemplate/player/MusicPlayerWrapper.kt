package com.code4galaxy.musicplayertemplate.player

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Wrapper around [ExoPlayer] that manages music playback operations.
 *
 * Provides a simplified API for playing, pausing, resuming, stopping,
 * seeking, and monitoring music playback. Playback state is exposed
 * through observable StateFlows so that the UI can react to changes.
 *
 * @property exoPlayer Media3 player used for audio playback.
 */




@Singleton
class MusicPlayerWrapper @Inject constructor(
    private val exoPlayer: ExoPlayer
) {


    /**
     * Indicates whether audio is currently playing.
     */

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()


    /**
     * Current playback position in milliseconds.
     */
    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition = _currentPosition.asStateFlow()


    /**
     * Duration of the currently loaded track.
     */

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



    /**
     * Loads and starts playing an audio track.
     *
     * Any existing position and duration state is reset before
     * the new media item is prepared.
     *
     * @param url URL of the audio stream to play.
     */

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


    /**
     * Pauses the currently playing track.
     */

    fun pause() {
        exoPlayer.pause()
    }


    /**
     * Resumes playback of the currently paused track.
     */


    fun resume() {
        exoPlayer.play()
    }


    /**
     * Stops playback and resets playback state.
     */

    fun stop() {

        exoPlayer.stop()

        _currentPosition.value = 0L
        _duration.value = 0L
        _isPlaying.value = false
    }



    /**
     * Moves playback to the requested position.
     *
     * @param position Target playback position in milliseconds.
     */

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }


    /**
    * Updates the exposed playback position using the player's
    * current position.
    */

    fun updatePosition() {
        _currentPosition.value =
            exoPlayer.currentPosition
    }


    /**
     * Releases the underlying player resources.
     *
     * The wrapper should not be used for playback after this call.
     */


    fun release() {
        exoPlayer.release()
    }
}