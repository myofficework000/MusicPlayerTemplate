package com.code4galaxy.musicplayertemplate.domain.useCase

import androidx.compose.ui.geometry.Offset
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject


/**
 * Use case responsible for retrieving trending music tracks.
 *
 * This class provides the domain-level operation for loading trending tracks
 * and delegates the actual data retrieval to [TrackRepository].
 *
 * @property repository Repository used to retrieve track data.
 */


class GetTrendingTracksUseCase @Inject constructor(private val repository: TrackRepository) {


    /**
     * Retrieves trending tracks using the given filters.
     *
     * @param genre Optional genre used to filter results.
     * @param limit Maximum number of tracks to retrieve.
     * @param time Time range used for determining trending tracks.
     * @param offset Number of tracks to skip.
     *
     * @return A list of trending [Track] objects.
     */



    suspend operator fun invoke(genre: String?, limit: Int = 10, time: String = "week", offset: Int) : List<Track>{
        return repository.getTrendingTracks(
            genre = genre,
            limit = limit,
            time = time,
            offset = offset
        )
    }
}