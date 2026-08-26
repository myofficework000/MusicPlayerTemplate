package com.code4galaxy.musicplayertemplate.data.remote.dto


import com.google.gson.annotations.SerializedName

data class UndergroundTrendingTracksResponse(
    @SerializedName("trackDto")
    val `data`: List<TrackDto>
)