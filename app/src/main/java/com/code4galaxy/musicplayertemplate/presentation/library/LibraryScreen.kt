package com.code4galaxy.musicplayertemplate.presentation.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.code4galaxy.musicplayertemplate.presentation.home.TrackItem

@Composable
fun LibraryScreen(
    onTrackClick: (String) -> Unit,
    viewModel: LibraryViewModel = hiltViewModel()
) {

    val favorites by viewModel.favorites.collectAsState()

    val backgroundBrush =
        Brush.verticalGradient(
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
            text = "Library",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Your favorite tracks",
            color = Color(0xFFB8B2C2),
            fontSize = 15.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        if (favorites.isEmpty()) {

            Text(
                text = "No favorite tracks yet",
                color = Color(0xFFB8B2C2)
            )

        } else {

            LazyColumn(
                contentPadding = PaddingValues(
                    bottom = 24.dp
                ),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = favorites,
                    key = {
                        it.id
                    }
                ) { track ->

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
    }
}