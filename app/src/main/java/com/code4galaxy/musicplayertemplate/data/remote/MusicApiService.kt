package com.code4galaxy.musicplayertemplate.data.remote

import com.code4galaxy.musicplayertemplate.data.remote.dto.SearchTracksResponse
import com.code4galaxy.musicplayertemplate.data.remote.dto.TrackDetailsResponse
import com.code4galaxy.musicplayertemplate.data.remote.dto.TrendingTracksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicApiService {

    @GET("/tracks/trending")
    suspend fun getTrendingTracks(
        @Query("genre") genre: String,
        @Query("time") time: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): TrendingTracksResponse

    @GET("/tracks/search")
    suspend fun getSearchTracks(
        @Query("query") query: String,
        @Query("genre") genre: String,
        @Query("time") time: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("sortMethod") sortMethod: String,
        @Query("mood") mood: Int
    ): SearchTracksResponse

    @GET("/tracks/{track_id}")
    suspend fun getTrackById(
        @Path("track_id") trackId: String
    ): TrackDetailsResponse

    @GET("/tracks/trending/underground")
    suspend fun getUndergroundTracks(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): TrendingTracksResponse
}