package com.code4galaxy.musicplayertemplate.domain.model


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
