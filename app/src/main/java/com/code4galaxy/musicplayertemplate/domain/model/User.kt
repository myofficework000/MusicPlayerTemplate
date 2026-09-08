package com.code4galaxy.musicplayertemplate.domain.model

data class User(
    val uid: String,
    val email: String,
    val isEmailVerified: Boolean
)
