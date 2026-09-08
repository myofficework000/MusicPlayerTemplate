package com.code4galaxy.musicplayertemplate.data

import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor() : FavoritesRepository {

    private val _favorites =
        MutableStateFlow<List<Track>>(emptyList())

    override val favorites: StateFlow<List<Track>> =
        _favorites.asStateFlow()

    override fun addFavorite(track: Track) {

        if (_favorites.value.none { it.id == track.id }) {
            _favorites.value =
                _favorites.value + track
        }
    }

    override fun removeFavorite(track: Track) {

        _favorites.value =
            _favorites.value.filter {
                it.id != track.id
            }
    }

    override fun toggleFavorite(track: Track) {

        if (isFavorite(track.id)) {
            removeFavorite(track)
        } else {
            addFavorite(track)
        }
    }

    override fun isFavorite(trackId: String): Boolean {

        return _favorites.value.any {
            it.id == trackId
        }
    }
}