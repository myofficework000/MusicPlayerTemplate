package com.code4galaxy.musicplayertemplate.presentation.library

import androidx.lifecycle.ViewModel
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {

    val favorites: StateFlow<List<Track>> =
        repository.favorites

    fun addFavorite(track: Track) {
        repository.addFavorite(track)
    }

    fun removeFavorite(track: Track) {
        repository.removeFavorite(track)
    }

    fun toggleFavorite(track: Track) {
        repository.toggleFavorite(track)
    }

    fun isFavorite(trackId: String): Boolean {
        return repository.isFavorite(trackId)
    }
}