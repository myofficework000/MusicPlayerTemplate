package com.code4galaxy.musicplayertemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.useCase.GetTrackDetailsUseCase
import com.code4galaxy.musicplayertemplate.domain.useCase.GetTrendingTracksUseCase
import com.code4galaxy.musicplayertemplate.domain.useCase.GetUndergroundTracks
import com.code4galaxy.musicplayertemplate.domain.useCase.SearchTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for music-related UI state and user actions.
 *
 * Coordinates domain use cases for retrieving tracks and exposes the
 * resulting loading, success, and error states to the Compose UI.
 *
 * The ViewModel acts as the presentation-layer bridge between the
 * application's use cases and music screens.
 */
@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getTrackDetailsUseCase: GetTrackDetailsUseCase,
    private val getTrendingTracksUseCase: GetTrendingTracksUseCase,
    private val getUndergroundTracksUseCase: GetUndergroundTracks,
    private val searchTracksUseCase: SearchTracksUseCase
) : ViewModel() {

    private val _trackUiState =
        MutableStateFlow<UiState<Track>>(UiState.Idle)

    val trackUiState: StateFlow<UiState<Track>> =
        _trackUiState.asStateFlow()


    private val _trendingTracksUiState =
        MutableStateFlow<UiState<List<Track>>>(UiState.Idle)

    val trendingTracksUiState: StateFlow<UiState<List<Track>>> =
        _trendingTracksUiState.asStateFlow()


    private val _undergroundTracksUiState =
        MutableStateFlow<UiState<List<Track>>>(UiState.Idle)

    val undergroundTracksUiState: StateFlow<UiState<List<Track>>> =
        _undergroundTracksUiState.asStateFlow()


    private val _searchTracksUiState =
        MutableStateFlow<UiState<List<Track>>>(UiState.Idle)

    val searchTracksUiState: StateFlow<UiState<List<Track>>> =
        _searchTracksUiState.asStateFlow()



    /**
     * Retrieves details for the selected track.
     *
     * @param trackId Unique identifier of the track.
     */
    fun getTrackDetails(trackId: String) {

        viewModelScope.launch {

            _trackUiState.value = UiState.Loading

            try {

                val track =
                    getTrackDetailsUseCase(trackId)

                _trackUiState.value =
                    UiState.Success(track)

            } catch (e: Exception) {

                _trackUiState.value =
                    UiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }


    /**
    * Loads trending tracks using the selected filters.
    */
    fun getTrendingTracks() {

        viewModelScope.launch {

            _trendingTracksUiState.value =
                UiState.Loading

            try {

                val tracks =
                    getTrendingTracksUseCase(
                        genre = null,
                        limit = 10,
                        time = "week",
                        offset = 0
                    )

                _trendingTracksUiState.value =
                    UiState.Success(tracks)

            } catch (e: Exception) {

                _trendingTracksUiState.value =
                    UiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }


    /**
     * Loads underground trending tracks.
     */
    fun getUndergroundTracks() {

        viewModelScope.launch {

            _undergroundTracksUiState.value =
                UiState.Loading

            try {

                val tracks =
                    getUndergroundTracksUseCase(
                        limit = 10,
                        offset = 0
                    )

                _undergroundTracksUiState.value =
                    UiState.Success(tracks)

            } catch (e: Exception) {

                _undergroundTracksUiState.value =
                    UiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }


    /**
     * Searches for tracks matching the provided query.
     *
     * @param query Search text entered by the user.
     */
    fun searchTracks(query: String) {

        if (query.isBlank()) {
            _searchTracksUiState.value =
                UiState.Idle
            return
        }

        viewModelScope.launch {

            _searchTracksUiState.value =
                UiState.Loading

            try {

                val tracks =
                    searchTracksUseCase(
                        query = query,
                        limit = 10,
                        offset = 0,
                        genre = null,
                        sortMethod = null,
                        mood = null
                    )

                _searchTracksUiState.value =
                    UiState.Success(tracks)

            } catch (e: Exception) {

                _searchTracksUiState.value =
                    UiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }
}