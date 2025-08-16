package com.example.jikananimeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room database for the Jikan Anime App.
 * This database contains two entities: AnimeEntity and AnimeDetailEntity.
 * It provides DAOs for accessing anime and anime detail data.
 *
 * @property version The version of the database schema.
 * @property exportSchema Indicates whether to export the database schema.
 */
@Database(
    entities = [AnimeEntity::class, AnimeDetailEntity::class],
    version = 2,
    exportSchema = false
)
abstract class JikanDatabase : RoomDatabase() {

    /**
     * Provides access to the AnimeDao for performing CRUD operations on AnimeEntity.
     *
     * @return AnimeDao instance.
     */
    abstract fun animeDao(): AnimeDao

    /**
     * Provides access to the AnimeDetailDao for performing CRUD operations on AnimeDetailEntity.
     *
     * @return AnimeDetailDao instance.
     */
    abstract fun animeDetailDao(): AnimeDetailDao
}