package com.example.jikananimeapp.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JikanAnimeRepository @Inject constructor(
    private val api: JikanApiService,
    private val animeDao: AnimeDao,
    private val animeDetailDao: AnimeDetailDao
) {

    /**
     * Retrieves a Flow of all anime entities from the local database.
     *
     * @return A Flow emitting a list of AnimeEntity objects.
     */
    fun getAnimeFlow() = animeDao.getAllAnime()

    /**
     * Refreshes the anime list from the API and updates the local database.
     * Clears the existing anime data in the database and inserts the new data.
     */
    suspend fun refreshFromApi() = withContext(Dispatchers.IO) {
        try {
            val response = api.getTopAnime()
            val entities = response.data.mapIndexed { idx, dto ->
                AnimeEntity(
                    mal_id = dto.mal_id,
                    title = dto.title,
                    image_url = dto.images.jpg.image_url,
                    score = dto.score,
                    synopsis = dto.synopsis,
                    episodes = dto.episodes,
                    index = idx
                )
            }
            animeDao.clearAll()
            animeDao.insertAnimeList(entities)
        } catch (e: Exception) {
            Log.e("Repository", "Failed refresh: ${e.message}")
        }
    }

    /**
     * Retrieves a Flow of anime detail for a specific anime ID from the local database.
     *
     * @param id The ID of the anime.
     * @return A Flow emitting an AnimeDetailEntity object or null if not found.
     */
    fun getDetailFlow(id: Int): Flow<AnimeDetailEntity?> = animeDetailDao.getDetail(id)

    /**
     * Refreshes the anime detail for a specific anime ID from the API and updates the local database.
     * Fetches the anime detail and character information, processes the data, and stores it in the database.
     *
     * @param id The ID of the anime to refresh.
     */
    suspend fun refreshDetail(id: Int) = withContext(Dispatchers.IO) {
        try {
            val detailDto = api.getAnimeDetail(id).data
            val characterResp = api.getAnimeCharacters(id)

            val castList = characterResp.data.take(6).mapNotNull { role ->
                val charName = role.character?.name
                val actorName = role.voice_actors?.firstOrNull()?.person?.name
                if (charName != null && actorName != null) {
                    "$charName ($actorName)"
                } else charName
            }
            val castString = castList.joinToString(", ")

            val entity = AnimeDetailEntity(
                mal_id = detailDto.mal_id,
                title = detailDto.title,
                synopsis = detailDto.synopsis,
                episodes = detailDto.episodes,
                score = detailDto.score,
                image_url = detailDto.images?.jpg?.image_url,
                trailer_embed_url = detailDto.trailer?.embed_url,
                youtube_id = detailDto.trailer?.youtube_id,
                genres = detailDto.genres?.joinToString(", ") { it.name },
                mainCast = castString
            )
            animeDetailDao.insertDetail(entity)
        } catch (e: Exception) {
            Log.e("Repository", "Failed detail fetch: ${e.message}")
        }
    }

}
