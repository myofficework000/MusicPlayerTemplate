package com.code4galaxy.musicplayertemplate.domain.useCase

import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject


/**
 * Use case responsible for retrieving underground trending tracks.
 *
 * @property repository Repository used to retrieve track data.
 */


class GetUndergroundTracks @Inject constructor(private val repository: TrackRepository) {


    /**
     * Retrieves underground trending tracks.
     *
     * @param limit Maximum number of tracks to retrieve.
     * @param offset Number of tracks to skip.
     *
     * @return A list of underground [Track] objects.
     */


    suspend operator fun invoke(limit: Int = 10, offset: Int) : List<Track>{
        return  repository.getUndergroundTracks(
            limit = limit,
            offset = offset
        )
    }
}