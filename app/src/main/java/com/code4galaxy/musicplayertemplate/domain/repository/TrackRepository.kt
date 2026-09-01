package com.code4galaxy.musicplayertemplate.domain.repository

import com.code4galaxy.musicplayertemplate.domain.model.Track


/**
 * Defines the contract for accessing track data.
 *
 * The domain layer depends on this interface instead of directly depending
 * on a remote API implementation. The actual implementation is provided
 * by the data layer.
 */


interface TrackRepository {


    /**
     * Retrieves a list of currently trending tracks.
     *
     * @param genre Optional genre used to filter the tracks.
     * @param limit Maximum number of tracks to retrieve.
     * @param time Time range used to determine trending tracks.
     * @param offset Number of tracks to skip.
     *
     * @return A list of trending [Track] objects.
     */


    suspend fun getTrendingTracks(
        genre:String? = null,
        limit: Int = 10,
        time: String = "week",
        offset: Int = 0): List<Track>




    /**
     * Retrieves underground trending tracks.
     *
     * @param limit Maximum number of tracks to retrieve.
     * @param offset Number of tracks to skip.
     *
     * @return A list of underground [Track] objects.
     */


    suspend fun getUndergroundTracks(
        limit: Int = 10,
        offset: Int = 0): List<Track>



    /**
     * Searches for tracks matching the provided query and filters.
     *
     * @param query Search text entered by the user.
     * @param limit Maximum number of results to retrieve.
     * @param offset Number of results to skip.
     * @param time Optional time filter.
     * @param genre Optional genre filter.
     * @param sortMethod Optional sorting method.
     * @param mood Optional filter.
     *
     * @return A list of tracks matching the search criteria.
     */



    suspend fun searchTracks(
        query: String,
        limit: Int = 10,
        offset: Int = 0,
        time: String? = null,
        genre: String? = null,
        sortMethod: String? = null,
        mood: String? = null): List<Track>



    /**
     * Retrieves complete information for a specific track.
     *
     * @param trackId Unique identifier of the requested track.
     *
     * @return The requested [Track].
     */




    suspend fun getTrackDetails(trackId: String): Track

}