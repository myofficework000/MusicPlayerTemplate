package com.code4galaxy.musicplayertemplate.domain.useCase

import androidx.compose.ui.geometry.Offset
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject

class GetTrendingTracksUseCase @Inject constructor(private val repository: TrackRepository) {
    suspend operator fun invoke(genre: String?, limit: Int = 10, time: String = "week", offset: Int) : List<Track>{
        return repository.getTrendingTracks(
            genre = genre,
            limit = limit,
            time = time,
            offset = offset
        )
    }
}