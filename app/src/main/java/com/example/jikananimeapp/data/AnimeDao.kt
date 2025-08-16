package com.example.jikananimeapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface AnimeDao {

    /**
     * Retrieves all anime entities from the database, ordered by their index in ascending order.
     * @return A [Flow] emitting a list of [AnimeEntity] objects.
     */
    @Query("SELECT * FROM anime_table ORDER BY `index` ASC")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    /**
     * Inserts a list of anime entities into the database.
     * If a conflict occurs, the existing entry will be replaced.
     * @param list The list of [AnimeEntity] objects to insert.
     * @return A list of row IDs for the inserted entities.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimeList(list: List<AnimeEntity>): List<Long>

    /**
     * Deletes all entries from the anime table.
     * @return The number of rows affected by the operation.
     */
    @Query("DELETE FROM anime_table")
    fun clearAll(): Int
}