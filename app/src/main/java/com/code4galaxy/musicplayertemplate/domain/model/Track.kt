package com.code4galaxy.musicplayertemplate.domain.model


/**
 * Represents a music track used throughout the application
 *
 * This is domain model used by the presentation and domain layers.
 * Track data received from the remote API is converted into the model before being exposed by the UI.
 *
 * @property id unique identifier of the track.
 * @property title Title of the track.
 * @property artistName Name of the artist who created the track.
 * @property description Optional description of the track.
 * @property genre Genre associated with the track.
 * @property duration Duration of the track in seconds.
 * @property streamUrl URL used to stream the audio.
 * @property tags Optional list of tags associated with the track.
 * @property artworkUrl URL of the track artwork image.
 */
data class Track(
    val id: String,
    val title: String,
    val artistName: String,
    val description: String?,
    val genre: String?,
    val duration: Int,
    val streamUrl: String,
    val tags: List<String>?,
    val artworkUrl: String?
)
