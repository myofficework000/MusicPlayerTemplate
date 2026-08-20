package com.code4galaxy.musicplayertemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

import com.code4galaxy.musicplayertemplate.ui.MusicPlayerScreen

@Serializable
object Home

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            MusicPlayerScreen()
        }
    }
}