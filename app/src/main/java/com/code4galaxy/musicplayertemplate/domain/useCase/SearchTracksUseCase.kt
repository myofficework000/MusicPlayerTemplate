package com.code4galaxy.musicplayertemplate.domain.useCase

import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject
/**
 * Use case responsible for retrieving result based on the search query.
 *
 * @property repository Repository used to retrieve track data.
 */
class SearchTracksUseCase @Inject constructor(private val repository: TrackRepository) {
    /**
     * Searches for tracks matching the provided query.
     *
     * Optional parameters can be used to control
     * filtering, and sorting of the search results.
     *
     * @param query The text used to search for tracks.
     * @param limit Maximum number of tracks to return. Defaults to 10.
     * @param offset Number of tracks to skip before returning results.
     * @param genre Optional genre used to filter the search results.
     * @param sortMethod Optional sorting method used to order the results.
     * @param mood Optional mood used to filter the search results.
     *
     * @return A list of [Track] objects matching the search criteria.
     */
    suspend operator fun invoke(query: String, limit:Int = 10, offset:Int, genre: String? ,sortMethod: String? ,mood: String?): List<Track>{
       return repository.searchTracks(
           query,
           limit = limit,
           offset = offset,
           genre = genre,
           sortMethod = sortMethod,
           mood = mood
       )
    }
}