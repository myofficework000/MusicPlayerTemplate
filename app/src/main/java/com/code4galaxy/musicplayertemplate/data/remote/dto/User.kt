package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("album_count")
    val albumCount: Int,
    @SerializedName("allow_ai_attribution")
    val allowAiAttribution: Boolean,
    @SerializedName("artist_coin_badge")
    val artistCoinBadge: ArtistCoinBadge,
    @SerializedName("artist_pick_track_id")
    val artistPickTrackId: String,
    @SerializedName("associated_sol_wallets_balance")
    val associatedSolWalletsBalance: String,
    @SerializedName("associated_wallets_balance")
    val associatedWalletsBalance: String,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("blocknumber")
    val blocknumber: Int,
    @SerializedName("coin_flair_mint")
    val coinFlairMint: String,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,
    @SerializedName("cover_photo_cids")
    val coverPhotoCids: Any,
    @SerializedName("cover_photo_legacy")
    val coverPhotoLegacy: Any,
    @SerializedName("cover_photo_sizes")
    val coverPhotoSizes: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("creator_node_endpoint")
    val creatorNodeEndpoint: String,
    @SerializedName("current_user_followee_follow_count")
    val currentUserFolloweeFollowCount: Int,
    @SerializedName("does_current_user_follow")
    val doesCurrentUserFollow: Boolean,
    @SerializedName("does_current_user_subscribe")
    val doesCurrentUserSubscribe: Boolean,
    @SerializedName("does_follow_current_user")
    val doesFollowCurrentUser: Boolean,
    @SerializedName("donation")
    val donation: Any,
    @SerializedName("erc_wallet")
    val ercWallet: String,
    @SerializedName("followee_count")
    val followeeCount: Int,
    @SerializedName("follower_count")
    val followerCount: Int,
    @SerializedName("handle")
    val handle: String,
    @SerializedName("handle_lc")
    val handleLc: String,
    @SerializedName("has_collectibles")
    val hasCollectibles: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("instagram_handle")
    val instagramHandle: String,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("is_deactivated")
    val isDeactivated: Boolean,
    @SerializedName("is_storage_v2")
    val isStorageV2: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("payout_wallet")
    val payoutWallet: String,
    @SerializedName("playlist_count")
    val playlistCount: Int,
    @SerializedName("profile_picture")
    val profilePicture: ProfilePicture,
    @SerializedName("profile_picture_cids")
    val profilePictureCids: Any,
    @SerializedName("profile_picture_legacy")
    val profilePictureLegacy: Any,
    @SerializedName("profile_picture_sizes")
    val profilePictureSizes: String,
    @SerializedName("profile_type")
    val profileType: Any,
    @SerializedName("repost_count")
    val repostCount: Int,
    @SerializedName("spl_usdc_payout_wallet")
    val splUsdcPayoutWallet: Any,
    @SerializedName("spl_usdc_wallet")
    val splUsdcWallet: Any,
    @SerializedName("spl_wallet")
    val splWallet: String,
    @SerializedName("supporter_count")
    val supporterCount: Int,
    @SerializedName("supporting_count")
    val supportingCount: Int,
    @SerializedName("tiktok_handle")
    val tiktokHandle: String,
    @SerializedName("total_audio_balance")
    val totalAudioBalance: Int,
    @SerializedName("total_balance")
    val totalBalance: String,
    @SerializedName("track_count")
    val trackCount: Int,
    @SerializedName("twitter_handle")
    val twitterHandle: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("verified_with_instagram")
    val verifiedWithInstagram: Boolean,
    @SerializedName("verified_with_tiktok")
    val verifiedWithTiktok: Boolean,
    @SerializedName("verified_with_twitter")
    val verifiedWithTwitter: Boolean,
    @SerializedName("wallet")
    val wallet: String,
    @SerializedName("waudio_balance")
    val waudioBalance: String,
    @SerializedName("website")
    val website: String
)
