package com.code4galaxy.musicplayertemplate.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Download(
    @SerializedName("url")
    val url: String,
    @SerializedName("mirrors")
    val mirrors: List<String>
)