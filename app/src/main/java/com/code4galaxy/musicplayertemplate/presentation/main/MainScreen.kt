package com.code4galaxy.musicplayertemplate.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code4galaxy.musicplayertemplate.navigation.Home
import com.code4galaxy.musicplayertemplate.navigation.Library
import com.code4galaxy.musicplayertemplate.navigation.Login
import com.code4galaxy.musicplayertemplate.navigation.Main
import com.code4galaxy.musicplayertemplate.navigation.Player
import com.code4galaxy.musicplayertemplate.navigation.Profile
import com.code4galaxy.musicplayertemplate.navigation.Search
import com.code4galaxy.musicplayertemplate.presentation.home.HomeScreen
import com.code4galaxy.musicplayertemplate.presentation.library.LibraryScreen
import com.code4galaxy.musicplayertemplate.presentation.profile.ProfileScreen
import com.code4galaxy.musicplayertemplate.presentation.search.SearchScreen

@Composable
fun MainScreen(
    rootNavController: NavHostController
) {

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = bottomNavController
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = bottomNavController,
            startDestination = Home,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<Home> {

                HomeScreen(
                    onTrackClick = { trackId ->

                        rootNavController.navigate(
                            Player(trackId)
                        )
                    }
                )
            }

            composable<Search> {

                SearchScreen(
                    onTrackClick = { trackId ->

                        rootNavController.navigate(
                            Player(trackId)
                        )
                    }
                )
            }

            composable<Library> {

                LibraryScreen(
                    onTrackClick = { trackId ->

                        rootNavController.navigate(
                            Player(trackId)
                        )
                    }
                )
            }

            composable<Profile> {

                ProfileScreen(
                    onLogoutClick = {

                        rootNavController.navigate(Login) {

                            popUpTo(Main) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}