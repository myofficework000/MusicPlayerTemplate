package com.code4galaxy.musicplayertemplate.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.code4galaxy.musicplayertemplate.presentation.home.TrackItem
import com.code4galaxy.musicplayertemplate.presentation.library.LibraryViewModel

@Composable
fun SearchScreen(
    onTrackClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
    libraryViewModel: LibraryViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val favorites by libraryViewModel.favorites.collectAsState()

    var query by remember {
        mutableStateOf("")
    }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F0F12),
            Color(0xFF21102F)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Search",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Find songs and artists you love",
            color = Color(0xFFB8B2C2),
            fontSize = 15.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Search songs, artists...")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.searchTracks(query)
                }
            ),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                focusedContainerColor = Color(0xFF2A2630),
                unfocusedContainerColor = Color(0xFF2A2630),

                focusedBorderColor = Color(0xFFB56CFF),
                unfocusedBorderColor = Color(0xFF3A3443),

                focusedLeadingIconColor = Color(0xFFB56CFF),
                unfocusedLeadingIconColor = Color(0xFF8F8798),

                focusedPlaceholderColor = Color(0xFF8F8798),
                unfocusedPlaceholderColor = Color(0xFF8F8798),

                cursorColor = Color(0xFFB56CFF)
            )
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        when {

            uiState.isLoading -> {

                CircularProgressIndicator(
                    color = Color(0xFFB56CFF)
                )
            }

            uiState.error != null -> {

                Text(
                    text = uiState.error ?: "Search failed",
                    color = Color(0xFFFF6B6B),
                    fontSize = 14.sp
                )
            }

            uiState.tracks.isNotEmpty() -> {

                Text(
                    text = "Search Results",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                LazyColumn(
                    contentPadding = PaddingValues(
                        bottom = 24.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        12.dp
                    )
                ) {

                    items(
                        items = uiState.tracks,
                        key = {
                            it.id
                        }
                    ) { track ->

                        TrackItem(
                            title = track.title,
                            artist = track.artistName,
                            artworkUrl = track.artworkUrl,

                            isFavorite = favorites.any {
                                it.id == track.id
                            },

                            onFavoriteClick = {
                                libraryViewModel.toggleFavorite(track)
                            },

                            onClick = {
                                onTrackClick(track.id)
                            }
                        )
                    }
                }
            }

            query.isNotBlank() -> {

                Text(
                    text = "No tracks found",
                    color = Color(0xFFB8B2C2),
                    fontSize = 15.sp
                )
            }

            else -> {

                Text(
                    text = "Search for your favorite music",
                    color = Color(0xFFB8B2C2),
                    fontSize = 15.sp
                )
            }
        }
    }
}