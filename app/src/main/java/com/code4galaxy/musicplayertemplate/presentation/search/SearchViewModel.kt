package com.code4galaxy.musicplayertemplate.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.useCase.SearchTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val isLoading: Boolean = false,
    val tracks: List<Track> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchTracksUseCase: SearchTracksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun searchTracks(query: String) {

        if (query.isBlank()) {
            _uiState.value = SearchUiState()
            return
        }

        viewModelScope.launch {

            _uiState.value = SearchUiState(
                isLoading = true
            )

            try {

                val tracks = searchTracksUseCase(
                    query = query,
                    limit = 20,
                    offset = 10,
                    genre = null,
                    sortMethod = null,
                    mood = null
                )

                _uiState.value = SearchUiState(
                    isLoading = false,
                    tracks = tracks
                )

            } catch (e: Exception) {

                _uiState.value = SearchUiState(
                    isLoading = false,
                    error = e.message ?: "Search failed"
                )
            }
        }
    }
}