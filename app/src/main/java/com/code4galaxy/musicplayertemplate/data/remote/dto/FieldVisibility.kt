package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FieldVisibility(
    @SerializedName("mood")
    val mood: Boolean,
    @SerializedName("tags")
    val tags: Boolean,
    @SerializedName("genre")
    val genre: Boolean,
    @SerializedName("share")
    val share: Boolean,
    @SerializedName("remixes")
    val remixes: Boolean,
    @SerializedName("play_count")
    val playCount: Boolean
)

