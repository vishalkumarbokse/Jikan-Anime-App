package com.example.jikananimeapp.di

import com.example.jikananimeapp.data.JikanApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger module that provides network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a singleton instance of Retrofit configured with the base URL
     * and a Gson converter factory.
     *
     * @return A configured [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides a singleton instance of [JikanApiService] created using the provided Retrofit instance.
     *
     * @param retrofit The [Retrofit] instance used to create the API service.
     * @return A [JikanApiService] instance.
     */
    @Provides
    @Singleton
    fun provideJikanApiService(retrofit: Retrofit): JikanApiService {
        return retrofit.create(JikanApiService::class.java)
    }
}