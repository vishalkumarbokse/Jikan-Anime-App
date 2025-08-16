package com.example.jikananimeapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_detail")
data class AnimeDetailEntity(
    @PrimaryKey
    val mal_id: Int,
    val title: String?,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val image_url: String?,
    val trailer_embed_url: String?,
    val youtube_id: String?,
    val genres: String?,
    val mainCast: String?
)