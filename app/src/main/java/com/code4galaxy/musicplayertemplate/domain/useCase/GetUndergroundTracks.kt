package com.code4galaxy.musicplayertemplate.domain.useCase

import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject

class GetUndergroundTracks @Inject constructor(private val repository: TrackRepository) {
    suspend operator fun invoke(limit: Int = 10, offset: Int) : List<Track>{
        return  repository.getUndergroundTracks(
            limit = limit,
            offset = offset
        )
    }
}