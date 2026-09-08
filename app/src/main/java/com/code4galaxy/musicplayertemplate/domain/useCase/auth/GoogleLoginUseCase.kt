package com.code4galaxy.musicplayertemplate.domain.useCase.auth

import com.code4galaxy.musicplayertemplate.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(
        credential: AuthCredential
    ): Result<Unit> {
        return repository.loginWithCredential(credential)
    }
}