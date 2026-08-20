package com.code4galaxy.musicplayertemplate.domain.useCase

import com.code4galaxy.musicplayertemplate.domain.model.Track
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import javax.inject.Inject

class SearchTracksUseCase @Inject constructor(private val repository: TrackRepository) {
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