package com.example.jikananimeapp.data

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service interface for accessing Jikan API endpoints.
 */
interface JikanApiService {

    /**
     * Fetches the top anime list.
     *
     * @return A [TopAnimeResponse] containing the list of top anime.
     */
    @GET("top/anime")
    suspend fun getTopAnime(): TopAnimeResponse

    /**
     * Fetches the details of a specific anime by its ID.
     *
     * @param id The ID of the anime to fetch details for.
     * @return An [AnimeDetailResponse] containing the anime details.
     */
    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: Int): AnimeDetailResponse

    /**
     * Fetches the characters of a specific anime by its ID.
     *
     * @param id The ID of the anime to fetch characters for.
     * @return An [AnimeCharacterResponse] containing the anime characters.
     */
    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") id: Int): AnimeCharacterResponse
}