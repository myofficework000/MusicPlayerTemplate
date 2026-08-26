package com.code4galaxy.musicplayertemplate.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SearchTracksResponse(
    @SerializedName("trackDto")
    val `data`: List<TrackDto>
)