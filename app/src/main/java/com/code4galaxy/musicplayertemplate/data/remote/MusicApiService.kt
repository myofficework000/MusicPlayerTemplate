package com.code4galaxy.musicplayertemplate.data.remote

import com.code4galaxy.musicplayertemplate.data.remote.dto.SearchTracksResponse
import com.code4galaxy.musicplayertemplate.data.remote.dto.TrackDetailsResponse
import com.code4galaxy.musicplayertemplate.data.remote.dto.TrendingTracksResponse
import com.code4galaxy.musicplayertemplate.data.remote.dto.UndergroundTrendingTracksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



/**
 * Retrofit service defining the music API endpoints.
 *
 * This service is used by the data layer to retrieve trending tracks,
 * search results, individual track details, and underground tracks.
 */


interface MusicApiService {



    /**
     * Retrieves trending tracks from the music API.
     *
     * @param genre Optional genre filter.
     * @param time Time range for trending results.
     * @param limit Maximum number of tracks to return.
     * @param offset Number of tracks to skip.
     *
     * @return A [TrendingTracksResponse] containing trending tracks.
     */


    @GET("tracks/trending")
    suspend fun getTrendingTracks(
        @Query("genre") genre: String?,
        @Query("time") time: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): TrendingTracksResponse



    /**
     * Searches for tracks using the provided search query and filters.
     *
     * @param query Search text entered by the user.
     * @param genre Optional genre filter.
     * @param time Optional time filter.
     * @param limit Maximum number of results.
     * @param offset Number of results to skip.
     * @param sortMethod Optional sorting method.
     * @param mood Optional mood filter.
     *
     * @return A [SearchTracksResponse] containing matching tracks.
     */


    @GET("tracks/search")
    suspend fun getSearchTracks(
        @Query("query") query: String,
        @Query("genre") genre: String?,
        @Query("time") time: String?,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("sortMethod") sortMethod: String?,
        @Query("mood") mood: String?
    ): SearchTracksResponse



    /**
     * Retrieves detailed information about a specific track.
     *
     * @param trackId Unique identifier of the track.
     *
     * @return A [TrackDetailsResponse] containing the track information.
     */

    @GET("tracks/{track_id}")
    suspend fun getTrackById(
        @Path("track_id") trackId: String
    ): TrackDetailsResponse



    /**
     * Retrieves underground trending tracks.
     *
     * @param limit Maximum number of tracks to retrieve.
     * @param offset Number of tracks to skip.
     *
     * @return An [UndergroundTrendingTracksResponse].
     */


    @GET("tracks/trending/underground")
    suspend fun getUndergroundTracks(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): UndergroundTrendingTracksResponse
}