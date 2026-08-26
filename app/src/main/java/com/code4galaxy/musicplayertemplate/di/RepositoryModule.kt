package com.code4galaxy.musicplayertemplate.di

import com.code4galaxy.musicplayertemplate.data.TrackRepositoryImpl
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTrackRepository(trackRepositoryImpl: TrackRepositoryImpl) : TrackRepository
}