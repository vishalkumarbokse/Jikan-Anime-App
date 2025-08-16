package com.example.jikananimeapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDetailDao {
    @Query("SELECT * FROM anime_detail WHERE mal_id = :id LIMIT 1")
    fun getDetail(id: Int): Flow<AnimeDetailEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detail: AnimeDetailEntity)
}