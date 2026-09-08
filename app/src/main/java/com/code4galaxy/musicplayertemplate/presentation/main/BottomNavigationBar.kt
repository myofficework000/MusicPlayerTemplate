package com.code4galaxy.musicplayertemplate.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.code4galaxy.musicplayertemplate.navigation.Home
import com.code4galaxy.musicplayertemplate.navigation.Library
import com.code4galaxy.musicplayertemplate.navigation.Profile
import com.code4galaxy.musicplayertemplate.navigation.Search

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = backStackEntry?.destination

    NavigationBar(
        containerColor = Color(0xFF141117),
        tonalElevation = 0.dp
    ) {

        NavigationBarItem(
            selected = currentDestination?.route ==
                    Home::class.qualifiedName,

            onClick = {
                navController.navigate(Home) {

                    launchSingleTop = true
                    restoreState = true

                    popUpTo(Home) {
                        saveState = true
                    }
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },

            label = {
                Text("Home")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color(0xFF3A2150),
                unselectedIconColor = Color(0xFF8F8798),
                unselectedTextColor = Color(0xFF8F8798)
            )
        )

        NavigationBarItem(
            selected = currentDestination?.route ==
                    Search::class.qualifiedName,

            onClick = {
                navController.navigate(Search) {

                    launchSingleTop = true
                    restoreState = true

                    popUpTo(Home) {
                        saveState = true
                    }
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },

            label = {
                Text("Search")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color(0xFF3A2150),
                unselectedIconColor = Color(0xFF8F8798),
                unselectedTextColor = Color(0xFF8F8798)
            )
        )

        NavigationBarItem(
            selected = currentDestination?.route ==
                    Library::class.qualifiedName,

            onClick = {
                navController.navigate(Library) {

                    launchSingleTop = true
                    restoreState = true

                    popUpTo(Home) {
                        saveState = true
                    }
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.LibraryMusic,
                    contentDescription = "Library"
                )
            },

            label = {
                Text("Library")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color(0xFF3A2150),
                unselectedIconColor = Color(0xFF8F8798),
                unselectedTextColor = Color(0xFF8F8798)
            )
        )

        NavigationBarItem(
            selected = currentDestination?.route ==
                    Profile::class.qualifiedName,

            onClick = {
                navController.navigate(Profile) {

                    launchSingleTop = true
                    restoreState = true

                    popUpTo(Home) {
                        saveState = true
                    }
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile"
                )
            },

            label = {
                Text("Profile")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = Color(0xFF3A2150),
                unselectedIconColor = Color(0xFF8F8798),
                unselectedTextColor = Color(0xFF8F8798)
            )
        )
    }
}