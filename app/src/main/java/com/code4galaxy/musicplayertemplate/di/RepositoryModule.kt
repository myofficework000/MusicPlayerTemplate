package com.code4galaxy.musicplayertemplate.di

import com.code4galaxy.musicplayertemplate.data.FavoritesRepositoryImpl
import com.code4galaxy.musicplayertemplate.data.TrackRepositoryImpl
import com.code4galaxy.musicplayertemplate.domain.repository.FavoritesRepository
import com.code4galaxy.musicplayertemplate.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * Hilt module responsible for repository dependency bindings.
 *
 * Connects repository interfaces from the domain layer with their
 * implementations from the data layer.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds [TrackRepositoryImpl] as the implementation of [TrackRepository].
     *
     * @param trackRepositoryImpl Concrete repository implementation.
     *
     * @return Repository implementation exposed as [TrackRepository].
     */
    @Binds
    @Singleton
    abstract fun bindTrackRepository(trackRepositoryImpl: TrackRepositoryImpl) : TrackRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository( impl: FavoritesRepositoryImpl ): FavoritesRepository
}