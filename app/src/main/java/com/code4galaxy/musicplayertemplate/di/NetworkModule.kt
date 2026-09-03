package com.code4galaxy.musicplayertemplate.di

import com.code4galaxy.musicplayertemplate.data.remote.MusicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Hilt dependency injection module responsible for networking dependencies.
 *
 * Provides singleton instances of [HttpLoggingInterceptor], [OkHttpClient],
 * [Retrofit], and [MusicApiService] used by the application.
 */



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.audius.co/v1/"



    /**
     * Provides an HTTP logging interceptor for inspecting network requests
     * and responses during development.
     *
     * @return Configured [HttpLoggingInterceptor].
     */


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }



    /**
     * Provides the application's shared HTTP client.
     *
     * @param loggingInterceptor Interceptor used for HTTP logging.
     * @return Configured [OkHttpClient].
     */


    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides the Retrofit client configured for the Audius API.
     *
     * @param okHttpClient HTTP client used by Retrofit.
     * @return Configured [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * Provides the Retrofit implementation of [MusicApiService].
     *
     * @param retrofit Retrofit instance used to create the service.
     * @return An implementation of [MusicApiService].
     */
    @Provides
    @Singleton
    fun provideMusicApiService(retrofit: Retrofit): MusicApiService{
        return retrofit.create(MusicApiService::class.java)
    }
}