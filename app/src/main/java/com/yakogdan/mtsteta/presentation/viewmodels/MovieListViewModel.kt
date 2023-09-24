package com.yakogdan.mtsteta.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.interactors.MovieListInteractor
import com.yakogdan.mtsteta.presentation.screenstates.MovieListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListInteractor: MovieListInteractor
) : ViewModel() {

    // MovieCard

    private val _movieListStateFlow =
        MutableStateFlow<MovieListScreenState>(MovieListScreenState.Loading)
    val movieListStateFlow = _movieListStateFlow.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _movieListStateFlow.value = MovieListScreenState.Error
        Log.d("myTAG", "exceptionHandler $throwable")
    }

    fun getMovieCards() {
        viewModelScope.launch(exceptionHandler) {
            _movieListStateFlow.value = MovieListScreenState.Loading
            delay(2000)
            movieListInteractor.getMovieCardsFromApi()
                .catch { _movieListStateFlow.emit(MovieListScreenState.Error) }
                .collect { list ->
                    if (list.isEmpty()) {
                        _movieListStateFlow.value = MovieListScreenState.Error
                    } else {
                        _movieListStateFlow.value = MovieListScreenState.Result(list)
                    }
                }
        }
    }

    // MovieGenre

    private val _movieGenresStateFlow = MutableStateFlow<List<MovieGenreDomain>>(listOf())
    val movieGenresStateFlow = _movieGenresStateFlow.asStateFlow()

    fun getMovieGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieGenres().collect {
                _movieGenresStateFlow.value = it
            }
        }
    }
}