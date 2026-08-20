package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoverPhoto(
    @SerializedName("2000x")
    val x: String,
    @SerializedName("640x")
    val y: String,
    @SerializedName("mirrors")
    val mirrors: List<String>
)