package com.example.jikananimeapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_table")
data class AnimeEntity(
    @PrimaryKey
    val mal_id: Int,
    val title: String?,
    val image_url: String?,
    val score: Double?,
    val synopsis: String?,
    val episodes: Int?,
    val index: Int
)
