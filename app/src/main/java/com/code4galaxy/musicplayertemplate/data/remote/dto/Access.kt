package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Access(
    @SerializedName("stream")
    val stream: Boolean,
    @SerializedName("download")
    val download: Boolean
)