package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Artwork(
    @SerializedName("150x150")
    val smallImageUrl: String,
    @SerializedName("480x480")
    val mediumImageUrl: String,
    @SerializedName("1000x1000")
    val largeImageUrl: String,
    @SerializedName("mirrors")
    val mirrors: List<String>
)