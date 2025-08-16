package com.example.jikananimeapp.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jikananimeapp.data.AnimeDetailEntity
import com.example.jikananimeapp.data.JikanAnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing anime details.
 *
 * This ViewModel is responsible for fetching and providing anime details
 * from the JikanAnimeRepository. It uses Hilt for dependency injection.
 *
 * @property repo The repository used to fetch anime details.
 */
@HiltViewModel
class AnimeDeatilsViewModel @Inject constructor(
    private val repo: JikanAnimeRepository
) : ViewModel() {

    /**
     * Refreshes the anime details for the given ID by invoking the repository.
     *
     * @param id The ID of the anime to refresh details for.
     */
    fun load(id: Int) {
        viewModelScope.launch {
            repo.refreshDetail(id)
        }
    }

    /**
     * Returns a [StateFlow] that emits the anime details for the given ID.
     *
     * @param id The ID of the anime to retrieve details for.
     * @return A [StateFlow] emitting the [AnimeDetailEntity] or null if not available.
     */
    fun detailState(id: Int): StateFlow<AnimeDetailEntity?> {
        return repo.getDetailFlow(id)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }
}