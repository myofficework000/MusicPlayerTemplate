package com.code4galaxy.musicplayertemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.code4galaxy.musicplayertemplate.presentation.home.HomeScreen
import com.code4galaxy.musicplayertemplate.ui.MusicPlayerScreen
import kotlinx.serialization.Serializable
/**
 * Navigation destination representing the application's home screen.
 */
@Serializable
object Home
/**
 * Navigation destination representing the music player screen.
 *
 * @property trackId Unique identifier of the track that should be played.
 */
@Serializable
data class Player(
    val trackId: String
)
/**
 *
 * Defines the main navigation graph for the application.
 *
 * The graph provides navigation between the home screen and the
 * music player screen.
 *
 * @param navController Controller responsible for application navigation.
 */
@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable<Home> {

            HomeScreen(
                onTrackClick = { trackId ->

                    navController.navigate(
                        Player(trackId)
                    )
                }
            )
        }

        composable<Player> { backStackEntry ->

            val player = backStackEntry.toRoute<Player>()

            MusicPlayerScreen(
                trackId = player.trackId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}