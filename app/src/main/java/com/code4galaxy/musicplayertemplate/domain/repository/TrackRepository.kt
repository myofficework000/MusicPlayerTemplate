package com.code4galaxy.musicplayertemplate.domain.repository

import com.code4galaxy.musicplayertemplate.domain.model.Track

interface TrackRepository {

    suspend fun getTrendingTracks(
        genre:String?,
        limit: Int = 10,
        time: String = "week",
        offset: Int): List<Track>

    suspend fun getUndergroundTracks(
        limit: Int = 10,
        offset: Int): List<Track>

    suspend fun searchTracks(
        query: String,
        limit: Int = 10,
        offset: Int,
        genre: String?,
        sortMethod: String?,
        mood: String?): List<Track>

    suspend fun getTrackDetails(trackId: String): Track

}