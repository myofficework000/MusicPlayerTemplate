package com.code4galaxy.musicplayertemplate.data

import com.code4galaxy.musicplayertemplate.data.mapper.toTrack
import com.code4galaxy.musicplayertemplate.data.mapper.toTrackList
import com.code4galaxy.musicplayertemplate.data.remote.MusicApiService
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject



/**
 * Implementation of [TrackRepository].
 *
 * Retrieves track data from [MusicApiService] and converts remote DTO
 * objects into domain [Track] models before returning them to the domain layer.
 *
 * @property musicApiService Remote API service used to communicate with music API.
 */


class TrackRepositoryImpl @Inject constructor(val musicApiService: MusicApiService) :
    TrackRepository {

    /**
     * Retrieves trending tracks from the remote API and maps them
     * into domain models.
     */
    override suspend fun getTrendingTracks(
        genre: String?,
        limit: Int,
        time: String,
        offset: Int
    ): List<Track> {
        val response = musicApiService.getTrendingTracks(
            genre = genre,
            limit = limit,
            time = time,
            offset = offset
        )
        return response.data.toTrackList()
    }


    /**
    * Retrieves underground tracks from the remote API.
    */
    override suspend fun getUndergroundTracks(
        limit: Int,
        offset: Int
    ): List<Track> {
        val response = musicApiService.getUndergroundTracks(
            limit = limit,
            offset = offset
        )
        return response.data.toTrackList()
    }


    /**
     * Searches remote tracks and maps the results into domain models.
     */


    override suspend fun searchTracks(
        query: String,
        limit: Int,
        offset: Int,
        time: String?,
        genre: String?,
        sortMethod: String?,
        mood: String?
    ): List<Track> {
        val response = musicApiService.getSearchTracks(
            genre = genre,
            limit = limit,
            offset = offset,
            query = query,
            time = time,
            sortMethod = sortMethod,
            mood = mood
        )
        return response.data.toTrackList()
    }



    /**
     * Retrieves and maps details for a single track.
     */


    override suspend fun getTrackDetails(trackId: String): Track {
        val response = musicApiService.getTrackById(
            trackId = trackId
        )
        return response.data.toTrack()
    }

}