package com.code4galaxy.musicplayertemplate.domain.useCase.auth

import com.code4galaxy.musicplayertemplate.domain.repository.AuthRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit>{
        return authRepository.signup(email, password)
    }
}