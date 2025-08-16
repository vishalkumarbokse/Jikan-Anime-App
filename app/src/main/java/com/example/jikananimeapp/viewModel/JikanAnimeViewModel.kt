package com.example.jikananimeapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jikananimeapp.data.AnimeEntity
import com.example.jikananimeapp.data.JikanAnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JikanAnimeViewModel @Inject constructor(
    private val repository: JikanAnimeRepository
) : ViewModel() {

    /**
     * A `StateFlow` that emits the list of anime entities.
     * It collects data from the repository's `getAnimeFlow` method and
     * starts lazily with an empty list.
     */
    val animeList: StateFlow<List<AnimeEntity>> =
        repository.getAnimeFlow()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    /**
     * Refreshes the anime data by invoking the repository's `refreshFromApi` method.
     * This method is executed within the `viewModelScope` coroutine scope.
     */
    fun refresh() {
        viewModelScope.launch {
            repository.refreshFromApi()
        }
    }
}
