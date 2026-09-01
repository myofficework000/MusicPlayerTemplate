package com.code4galaxy.musicplayertemplate.data.mapper

import com.code4galaxy.musicplayertemplate.data.remote.dto.TrackDto
import com.code4galaxy.musicplayertemplate.domain.model.Track



/**
 * Converts a remote [TrackDto] into the domain [Track] model.
 *
 * The mapper isolates API-specific data structures from the domain layer.
 * It also converts comma-separated tags into a list and removes empty values.
 *
 * @return A domain [Track] containing the mapped track information.
 */


fun TrackDto.toTrack(): Track{
    return Track(
        id = id,
        title = title,
        artistName = user.name,
        description = description?.takeIf(String::isNotEmpty),
        genre = genre,
        duration = duration,
        streamUrl = stream?.url ?:"",
        tags = tags
            ?.split(",")
            ?.map(String::trim)
            ?.filter(String::isNotEmpty)
            ?.takeIf(List<String>::isNotEmpty),
        artworkUrl = artwork.largeImageUrl,
    )
}



/**
 * Converts a list of remote [TrackDto] objects into domain [Track] objects.
 *
 * @return A list of mapped domain [Track] objects.
 */

fun List<TrackDto>.toTrackList(): List<Track> {
    return map { trackDto ->
        trackDto.toTrack()
    }
}