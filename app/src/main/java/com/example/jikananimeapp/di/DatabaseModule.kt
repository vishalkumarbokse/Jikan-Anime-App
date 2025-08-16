package com.example.jikananimeapp.di

import android.content.Context
import androidx.room.Room
import com.example.jikananimeapp.data.AnimeDao
import com.example.jikananimeapp.data.AnimeDetailDao
import com.example.jikananimeapp.data.JikanDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the singleton instance of the JikanDatabase.
     *
     * @param app The application context.
     * @return The JikanDatabase instance.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): JikanDatabase {
        return Room.databaseBuilder(
            app,
            JikanDatabase::class.java,
            "jikan.db"
        ).build()
    }

    /**
     * Provides the AnimeDao instance from the JikanDatabase.
     *
     * @param db The JikanDatabase instance.
     * @return The AnimeDao instance.
     */
    @Provides
    @Singleton
    fun provideAnimeDao(db: JikanDatabase): AnimeDao {
        return db.animeDao()
    }

    /**
     * Provides the AnimeDetailDao instance from the JikanDatabase.
     *
     * @param db The JikanDatabase instance.
     * @return The AnimeDetailDao instance.
     */
    @Provides
    fun provideAnimeDetailDao(db: JikanDatabase): AnimeDetailDao = db.animeDetailDao()

}
