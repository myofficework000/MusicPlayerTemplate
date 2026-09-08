package com.code4galaxy.musicplayertemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.code4galaxy.musicplayertemplate.presentation.auth.LoginScreen
import com.code4galaxy.musicplayertemplate.presentation.auth.RegisterScreen
import com.code4galaxy.musicplayertemplate.presentation.main.MainScreen
import com.code4galaxy.musicplayertemplate.ui.MusicPlayerScreen
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register

@Serializable
object Main

@Serializable
object Home

@Serializable
object Search

@Serializable
object Library

@Serializable
object Profile

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
        startDestination = Login
    ) {

        composable<Login> {

            LoginScreen(
                onLoginSuccess = {

                    navController.navigate(Main) {

                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                },

                onRegisterClick = {
                    navController.navigate(Register)
                }
            )
        }

        composable<Register> {

            RegisterScreen(
                onRegisterSuccess = {

                    navController.navigate(Main) {

                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                },

                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<Main> {

            MainScreen(
                rootNavController = navController
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