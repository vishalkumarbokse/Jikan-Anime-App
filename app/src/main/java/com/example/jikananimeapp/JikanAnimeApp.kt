package com.example.jikananimeapp

import dagger.hilt.android.HiltAndroidApp
import android.app.Application

/**
 * This is the application class for the Jikan Anime App.
 * It is annotated with `@HiltAndroidApp` to enable dependency injection
 * using Dagger Hilt throughout the application.
 */
@HiltAndroidApp
class JikanAnimeApp : Application()