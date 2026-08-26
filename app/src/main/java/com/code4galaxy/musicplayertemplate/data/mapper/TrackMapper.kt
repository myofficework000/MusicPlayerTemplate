package com.code4galaxy.musicplayertemplate.data.mapper

import com.code4galaxy.musicplayertemplate.data.remote.dto.TrackDto
import com.code4galaxy.musicplayertemplate.domain.model.Track

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

fun List<TrackDto>.toTrackList(): List<Track> {
    return map { trackDto ->
        trackDto.toTrack()
    }
}