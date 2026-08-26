package com.code4galaxy.musicplayertemplate.data

import com.code4galaxy.musicplayertemplate.data.mapper.toTrack
import com.code4galaxy.musicplayertemplate.data.mapper.toTrackList
import com.code4galaxy.musicplayertemplate.data.remote.MusicApiService
import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject


class TrackRepositoryImpl @Inject constructor(val musicApiService: MusicApiService) :
    TrackRepository {
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

    override suspend fun getTrackDetails(trackId: String): Track {
        val response = musicApiService.getTrackById(
            trackId = trackId
        )
        return response.data.toTrack()
    }

}