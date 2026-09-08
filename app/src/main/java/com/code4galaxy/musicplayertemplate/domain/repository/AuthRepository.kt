package com.code4galaxy.musicplayertemplate.domain.repository

import com.code4galaxy.musicplayertemplate.domain.model.User
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun signup(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun loginWithCredential(
        credential: AuthCredential
    ): Result<Unit>

    suspend fun logout()
}