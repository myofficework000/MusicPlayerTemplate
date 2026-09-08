package com.code4galaxy.musicplayertemplate.presentation.auth

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val message: String? = null,
    val error: String? = null
)
