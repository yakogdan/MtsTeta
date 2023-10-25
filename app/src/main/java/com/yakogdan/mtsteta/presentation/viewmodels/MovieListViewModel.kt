package com.yakogdan.mtsteta.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.interactors.MovieGenresInteractor
import com.yakogdan.domain.interactors.MovieListInteractor
import com.yakogdan.mtsteta.presentation.screenstates.MovieListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListInteractor: MovieListInteractor,
    private val movieGenresInteractor: MovieGenresInteractor
) : ViewModel() {

    // MovieCard

    private val _movieListStateFlow =
        MutableStateFlow<MovieListScreenState>(MovieListScreenState.Loading)
    val movieListStateFlow = _movieListStateFlow.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _movieListStateFlow.value = MovieListScreenState.Error
    }

    fun getMovieCards() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            movieListInteractor.getMovieCardsFromApi()
                .catch { _movieListStateFlow.emit(MovieListScreenState.Error) }
                .onStart { _movieListStateFlow.value = MovieListScreenState.Loading }
                .collect { list ->
                    if (list.isEmpty()) {
                        _movieListStateFlow.value = MovieListScreenState.Error
                    } else {
                        _movieListStateFlow.value = MovieListScreenState.Result(list)
                    }
                }
        }
    }

    fun addMovieCard(movieCard: MovieCardDomain) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            movieListInteractor.addMovieCard(movieCard)
        }
    }

    // MovieGenre

    private val _movieGenresStateFlow = MutableStateFlow<List<MovieGenreDomain>>(listOf())
    val movieGenresStateFlow = _movieGenresStateFlow.asStateFlow()

    fun getMovieGenres() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            movieGenresInteractor.getMovieGenresFromRepo().collect {
                _movieGenresStateFlow.value = it
            }
        }
    }
}