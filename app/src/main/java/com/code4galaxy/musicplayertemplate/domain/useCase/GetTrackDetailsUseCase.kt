package com.code4galaxy.musicplayertemplate.domain.useCase

import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject
/**
 * Use case responsible for retrieving the details of a specific track.
 *
 * @property repository Repository used to retrieve track information.
 */
class GetTrackDetailsUseCase @Inject constructor(private val repository: TrackRepository) {
    /**
     * Retrieves details for the requested track.
     *
     * @param trackId Unique identifier of the track.
     *
     * @return The requested [Track].
     */
    suspend operator fun invoke(trackId: String) : Track {
        return repository.getTrackDetails(trackId)
    }
}