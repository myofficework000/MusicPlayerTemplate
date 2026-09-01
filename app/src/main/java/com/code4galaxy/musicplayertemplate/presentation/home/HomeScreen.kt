package com.code4galaxy.musicplayertemplate.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle



/**
 * Displays the main home screen of the music player application.
 *
 * The screen allows the user to:
 * - View trending tracks.
 * - View underground tracks.
 * - Search for tracks.
 * - Select a track and navigate to the player screen.
 *
 * Track data is provided by [MusicViewModel]. The screen observes
 * trending, underground, and search UI states and updates the
 * interface according to loading, success, or error states.
 *
 * Trending and underground tracks are loaded when the screen
 * is first displayed.
 *
 * @param onTrackClick Callback invoked when the user selects a track.
 * The unique track ID is passed to the callback.
 * @param viewModel ViewModel responsible for loading tracks,
 * performing searches, and exposing UI state to this screen.
 */



@Composable
fun HomeScreen(
    onTrackClick: (String) -> Unit,
    viewModel: MusicViewModel = hiltViewModel()
) {
    val trendingState by viewModel.trendingTracksUiState.collectAsStateWithLifecycle()
    val undergroundState by viewModel.undergroundTracksUiState.collectAsStateWithLifecycle()
    val searchState by viewModel.searchTracksUiState.collectAsStateWithLifecycle()

    var isSearchVisible by remember {
        mutableStateOf(false)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.getTrendingTracks()
        viewModel.getUndergroundTracks()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF121212),
                        Color(0xFF1B1028),
                        Color(0xFF24123A)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            // Header
            androidx.compose.foundation.layout.Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Music",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Discover your favorite tracks",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )
                }

                IconButton(
                    onClick = {
                        isSearchVisible = !isSearchVisible
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }

            // Search Bar
            if (isSearchVisible) {

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        viewModel.searchTracks(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Search tracks...",
                            color = Color.Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFFBB86FC),
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color(0xFFBB86FC)
                    )
                )
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
                when (val state = searchState) {

                    UiState.Idle -> {
                    }

                    UiState.Loading -> {
                        Text(
                            text = "Searching...",
                            color = Color.White
                        )
                    }

                    is UiState.Success -> {

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            items(state.data) { track ->

                                TrackItem(
                                    title = track.title,
                                    artist = track.artistName,
                                    artworkUrl = track.artworkUrl,
                                    onClick = {
                                        onTrackClick(track.id)
                                    }
                                )
                            }
                        }
                    }

                    is UiState.Error -> {
                        Text(
                            text = state.message,
                            color = Color.Red
                        )
                    }
                }

            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // Trending Section
            Text(
                text = "Trending",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            when (val state = trendingState) {

                UiState.Idle -> {
                }

                UiState.Loading -> {
                    Text(
                        text = "Loading trending tracks...",
                        color = Color.White
                    )
                }

                is UiState.Success -> {

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        items(state.data) { track ->
                            TrackItem(
                                title = track.title,
                                artist = track.artistName,
                                artworkUrl = track.artworkUrl,
                                onClick = {
                                    onTrackClick(track.id)
                                }
                            )

                        }
                    }
                }

                is UiState.Error -> {
                    Text(
                        text = state.message,
                        color = Color.Red
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // Underground Section
            Text(
                text = "Underground",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            when (val state = undergroundState) {

                UiState.Idle -> {
                }

                UiState.Loading -> {
                    Text(
                        text = "Loading underground tracks...",
                        color = Color.White
                    )
                }

                is UiState.Success -> {

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        items(state.data) { track ->
                            TrackItem(
                                title = track.title,
                                artist = track.artistName,
                                artworkUrl = track.artworkUrl,
                                onClick = {
                                    onTrackClick(track.id)
                                }
                            )
                        }

                    }
                }

                is UiState.Error -> {
                    Text(
                        text = state.message,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

