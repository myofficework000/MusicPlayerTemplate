package com.code4galaxy.musicplayertemplate.domain.useCase.auth

import com.code4galaxy.musicplayertemplate.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(){
        return authRepository.logout()
    }
}