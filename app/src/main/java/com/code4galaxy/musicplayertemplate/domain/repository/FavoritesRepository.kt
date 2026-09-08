package com.code4galaxy.musicplayertemplate.domain.repository

import com.code4galaxy.musicplayertemplate.domain.model.Track
import kotlinx.coroutines.flow.StateFlow

interface FavoritesRepository {

    val favorites: StateFlow<List<Track>>

    fun addFavorite(track: Track)

    fun removeFavorite(track: Track)

    fun toggleFavorite(track: Track)

    fun isFavorite(trackId: String): Boolean
}