package com.code4galaxy.musicplayertemplate.domain.repository

import com.code4galaxy.musicplayertemplate.domain.model.Track

interface TrackRepository {

    suspend fun getTrendingTracks(
        genre:String? = null,
        limit: Int = 10,
        time: String = "week",
        offset: Int = 0): List<Track>

    suspend fun getUndergroundTracks(
        limit: Int = 10,
        offset: Int = 0): List<Track>

    suspend fun searchTracks(
        query: String,
        limit: Int = 10,
        offset: Int = 0,
        time: String? = null,
        genre: String? = null,
        sortMethod: String? = null,
        mood: String? = null): List<Track>

    suspend fun getTrackDetails(trackId: String): Track

}