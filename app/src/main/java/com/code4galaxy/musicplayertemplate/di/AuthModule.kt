package com.code4galaxy.musicplayertemplate.di

import com.code4galaxy.musicplayertemplate.data.AuthRepositoryImpl
import com.code4galaxy.musicplayertemplate.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    companion object{
        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }
    }
}