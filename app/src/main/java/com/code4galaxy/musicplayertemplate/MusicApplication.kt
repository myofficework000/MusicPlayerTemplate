package com.code4galaxy.musicplayertemplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the Music Player application.
 *
 * Initializes Hilt dependency injection for application's dependencies.
 */
@HiltAndroidApp
class MusicApplication : Application()