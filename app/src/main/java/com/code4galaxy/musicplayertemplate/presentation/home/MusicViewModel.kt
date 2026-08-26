package com.code4galaxy.musicplayertemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.useCase.GetTrackDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getTrackDetailsUseCase: GetTrackDetailsUseCase
): ViewModel() {

    private val _trackUiState = MutableStateFlow<UiState<Track>>(UiState.Idle)

    val trackUiState: StateFlow<UiState<Track>> = _trackUiState.asStateFlow()
    fun getTrackDetails(trackId: String) {
        viewModelScope.launch {
            _trackUiState.value = UiState.Loading

            try {
                val track = getTrackDetailsUseCase(trackId)

                _trackUiState.value = UiState.Success(track)
            } catch (e: Exception) {
                _trackUiState.value = UiState.Error(
                    e.message ?: "Something went wrong"
                )
            }
        }
    }
}