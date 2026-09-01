package com.code4galaxy.musicplayertemplate.player

import android.content.Intent
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



/**
 * Background media service responsible for exposing the application's
 * [MediaSession] to controllers.
 *
 * The service allows playback to participate in Android's media system
 * and manages the lifetime of the media session.
 */

@AndroidEntryPoint
class MusicService : MediaSessionService() {


    /**
     * Media session used to expose and control music playback.
     */
    @Inject
    lateinit var mediaSession: MediaSession



    /**
     * Returns the media session available to the requesting controller.
     *
     * @param controllerInfo Information about the controller requesting access.
     *
     * @return The application's active [MediaSession].
     */


    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }



    /**
     * Releases player and media-session resources when the service is destroyed.
     */

    override fun onDestroy() {
        mediaSession.run {
            player.release()
            release()
        }
        super.onDestroy()
    }
}