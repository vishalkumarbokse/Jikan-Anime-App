package com.example.jikananimeapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jikananimeapp.composeScreens.DetailScreen
import com.example.jikananimeapp.composeScreens.HomeListScreen
import com.example.jikananimeapp.ui.theme.JikanAnimeAppTheme
import com.example.jikananimeapp.viewModel.AnimeDeatilsViewModel
import com.example.jikananimeapp.viewModel.JikanAnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity serves as the entry point for the Jikan Anime App.
 * It sets up the navigation and UI using Jetpack Compose.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel for managing the list of anime
    private val listVM: JikanAnimeViewModel by viewModels()

    // ViewModel for managing anime details
    private val detailVM: AnimeDeatilsViewModel by viewModels()

    /**
     * Called when the activity is starting. Sets up the UI and navigation.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Enable hardware acceleration for the window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )
        super.onCreate(savedInstanceState)

        // Set the content view using Jetpack Compose
        setContent {
            val navController = rememberNavController()

            // Apply the app's theme
            JikanAnimeAppTheme {
                // Set up navigation with a NavHost
                NavHost(navController, startDestination = "home") {
                    // Define the "home" composable screen
                    composable("home") {
                        val animeList by listVM.animeList.collectAsState()

                        // Trigger a refresh of the anime list only once
                        LaunchedEffect(Unit) { listVM.refresh() }

                        // Display the home screen with the list of anime
                        HomeListScreen(
                            items = animeList,
                            onAnimeClick = { id -> navController.navigate("detail/$id") },
                            onRetry = { listVM.refresh() }
                        )
                    }

                    // Define the "detail" composable screen
                    composable(
                        route = "detail/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments!!.getInt("id")

                        // Load the details for the selected anime
                        LaunchedEffect(id) { detailVM.load(id) }

                        val detailItem by detailVM.detailState(id).collectAsState()

                        // Display the detail screen for the selected anime
                        DetailScreen(detail = detailItem)
                    }
                }
            }
        }
    }
}
