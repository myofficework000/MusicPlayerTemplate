package com.code4galaxy.musicplayertemplate.domain.useCase

import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject

class GetTrackDetailsUseCase @Inject constructor(private val repository: TrackRepository) {
    suspend operator fun invoke(trackId: String) : Track {
        return repository.getTrackDetails(trackId)
    }
}