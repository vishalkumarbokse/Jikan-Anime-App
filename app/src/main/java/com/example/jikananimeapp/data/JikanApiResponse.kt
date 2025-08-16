package com.example.jikananimeapp.data

data class TopAnimeResponse(
    val pagination: Pagination,
    val data: List<Anime>
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: PaginationItems
)

data class PaginationItems(
    val count: Int,
    val total: Int,
    val per_page: Int
)

data class Anime(
    val mal_id: Int,
    val url: String,
    val images: AnimeImages,
    val title: String,
    val title_english: String?,
    val episodes: Int?,
    val score: Double?,
    val synopsis: String?
)

data class AnimeImages(
    val jpg: ImageUrls,
    val webp: ImageUrls
)

data class ImageUrls(
    val image_url: String,
    val small_image_url: String?,
    val large_image_url: String?
)
