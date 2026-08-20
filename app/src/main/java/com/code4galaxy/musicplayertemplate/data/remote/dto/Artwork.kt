package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Artwork(
    @SerializedName("150x150")
    val x150: String,
    @SerializedName("480x480")
    val x480: String,
    @SerializedName("1000x1000")
    val x1000: String,
    @SerializedName("mirrors")
    val mirrors: List<String>
)