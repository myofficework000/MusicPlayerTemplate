package com.code4galaxy.musicplayertemplate.di

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module responsible for providing audio playback dependencies.
 *
 * Provides application-wide instances required for playback,
 * including [AudioAttributes], [ExoPlayer], and [MediaSession].
 */


@Module
@InstallIn(SingletonComponent::class)
object MediaModule {


    /**
     * Provides audio attributes configured for music playback.
     *
     * @return Audio attributes configured for media usage.
     */

    @Provides
    @Singleton
    fun provideAudioAttributes(): AudioAttributes {
        return AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }



    /**
     * Provides the application's shared [ExoPlayer] instance.
     *
     * @param context Application context used to create the player.
     * @param audioAttributes Audio configuration applied to the player.
     *
     * @return Configured [ExoPlayer].
     */


    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ): ExoPlayer {
        return ExoPlayer.Builder(context)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .build()
    }




    /**
     * Provides the application's [MediaSession].
     *
     * @param context Application context used to create the session.
     * @param player Player controlled by the media session.
     *
     * @return Configured [MediaSession].
     */


    @Provides
    @Singleton
    fun provideMediaSession(
        @ApplicationContext context: Context,
        player: ExoPlayer
    ): MediaSession {
        return MediaSession.Builder(context, player).build()
    }
}