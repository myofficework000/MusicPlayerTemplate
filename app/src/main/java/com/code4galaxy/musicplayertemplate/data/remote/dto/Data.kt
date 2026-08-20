package com.code4galaxy.musicplayertemplate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("track_id")
    val trackId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("track_cid")
    val trackCid: String,
    @SerializedName("preview_cid")
    val previewCid: Any,
    @SerializedName("orig_file_cid")
    val origFileCid: String,
    @SerializedName("orig_filename")
    val origFilename: String,
    @SerializedName("is_original_available")
    val isOriginalAvailable: Boolean,
    @SerializedName("mood")
    val mood: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("repost_count")
    val repostCount: Int,
    @SerializedName("favorite_count")
    val favoriteCount: Int,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("is_downloadable")
    val isDownloadable: Boolean,
    @SerializedName("play_count")
    val playCount: Int,
    @SerializedName("ddex_app")
    val ddexApp: Any,
    @SerializedName("pinned_comment_id")
    val pinnedCommentId: Any,
    @SerializedName("playlists_containing_track")
    val playlistsContainingTrack: List<Int>,
    @SerializedName("playlists_previously_containing_track")
    val playlistsPreviouslyContainingTrack: PlaylistsPreviouslyContainingTrack,
    @SerializedName("album_backlink")
    val albumBacklink: Any,
    @SerializedName("blocknumber")
    val blocknumber: Int,
    @SerializedName("create_date")
    val createDate: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("cover_art_sizes")
    val coverArtSizes: String,
    @SerializedName("credits_splits")
    val creditsSplits: Any,
    @SerializedName("isrc")
    val isrc: Any,
    @SerializedName("license")
    val license: Any,
    @SerializedName("iswc")
    val iswc: Any,
    @SerializedName("field_visibility")
    val fieldVisibility: FieldVisibility,
    @SerializedName("has_current_user_reposted")
    val hasCurrentUserReposted: Boolean,
    @SerializedName("has_current_user_saved")
    val hasCurrentUserSaved: Boolean,
    @SerializedName("is_scheduled_release")
    val isScheduledRelease: Boolean,
    @SerializedName("is_unlisted")
    val isUnlisted: Boolean,
    @SerializedName("stem_of")
    val stemOf: Any,
    @SerializedName("track_segments")
    val trackSegments: List<Any>,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("is_delete")
    val isDelete: Boolean,
    @SerializedName("cover_art")
    val coverArt: Any,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("ai_attribution_user_id")
    val aiAttributionUserId: Any,
    @SerializedName("allowed_api_keys")
    val allowedApiKeys: Any,
    @SerializedName("audio_upload_id")
    val audioUploadId: String,
    @SerializedName("preview_start_seconds")
    val previewStartSeconds: Any,
    @SerializedName("bpm")
    val bpm: Int,
    @SerializedName("is_custom_bpm")
    val isCustomBpm: Boolean,
    @SerializedName("musical_key")
    val musicalKey: String,
    @SerializedName("is_custom_musical_key")
    val isCustomMusicalKey: Boolean,
    @SerializedName("audio_analysis_error_count")
    val audioAnalysisErrorCount: Int,
    @SerializedName("comments_disabled")
    val commentsDisabled: Boolean,
    @SerializedName("ddex_release_ids")
    val ddexReleaseIds: Any,
    @SerializedName("artists")
    val artists: Any,
    @SerializedName("resource_contributors")
    val resourceContributors: Any,
    @SerializedName("indirect_resource_contributors")
    val indirectResourceContributors: Any,
    @SerializedName("rights_controller")
    val rightsController: Any,
    @SerializedName("copyright_line")
    val copyrightLine: Any,
    @SerializedName("producer_copyright_line")
    val producerCopyrightLine: Any,
    @SerializedName("parental_warning_type")
    val parentalWarningType: Any,
    @SerializedName("is_stream_gated")
    val isStreamGated: Boolean,
    @SerializedName("is_download_gated")
    val isDownloadGated: Boolean,
    @SerializedName("cover_original_song_title")
    val coverOriginalSongTitle: Any,
    @SerializedName("cover_original_artist")
    val coverOriginalArtist: Any,
    @SerializedName("is_owned_by_user")
    val isOwnedByUser: Boolean,
    @SerializedName("access_authorities")
    val accessAuthorities: Any,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("is_streamable")
    val isStreamable: Boolean,
    @SerializedName("artwork")
    val artwork: Artwork,
    @SerializedName("stream")
    val stream: Stream,
    @SerializedName("download")
    val download: Any,
    @SerializedName("preview")
    val preview: Any,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("collaborators")
    val collaborators: List<Any>,
    @SerializedName("pending_collaborators")
    val pendingCollaborators: List<Any>,
    @SerializedName("access")
    val access: Access,
    @SerializedName("followee_reposts")
    val followeeReposts: List<Any>,
    @SerializedName("followee_favorites")
    val followeeFavorites: List<Any>,
    @SerializedName("remix_of")
    val remixOf: RemixOf,
    @SerializedName("stream_conditions")
    val streamConditions: Any,
    @SerializedName("download_conditions")
    val downloadConditions: Any
)
