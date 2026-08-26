package com.code4galaxy.musicplayertemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.code4galaxy.musicplayertemplate.presentation.home.HomeScreen
import com.code4galaxy.musicplayertemplate.ui.MusicPlayerScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Player(
    val trackId: String
)

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