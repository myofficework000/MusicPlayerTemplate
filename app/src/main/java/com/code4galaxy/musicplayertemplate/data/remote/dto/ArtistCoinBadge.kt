package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArtistCoinBadge(
    @SerializedName("banner_image_url")
    val bannerImageUrl: Any,
    @SerializedName("logo_uri")
    val logoUri: String,
    @SerializedName("mint")
    val mint: String,
    @SerializedName("ticker")
    val ticker: String
)