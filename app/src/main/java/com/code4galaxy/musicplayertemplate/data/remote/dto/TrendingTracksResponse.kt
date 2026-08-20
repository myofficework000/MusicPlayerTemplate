package com.code4galaxy.musicplayertemplate.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TrendingTracksResponse(
    @SerializedName("data")
    val `data`: List<Data>
)