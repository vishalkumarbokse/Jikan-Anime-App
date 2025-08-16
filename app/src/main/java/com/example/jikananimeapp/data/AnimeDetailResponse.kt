package com.example.jikananimeapp.data

data class AnimeDetailResponse(val data: AnimeDetailDto)

data class AnimeDetailDto(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val rating: String?,
    val images: AnimeImages?,
    val trailer: TrailerDto?,
    val genres: List<Genre>?
)

data class Genre(val name: String)

data class TrailerDto(
    val youtube_id: String,
    val url: String?,
    val embed_url: String?
)
