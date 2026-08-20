package com.code4galaxy.musicplayertemplate.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TrackDetailsResponse(
    @SerializedName("data")
    val `data`: Data
)