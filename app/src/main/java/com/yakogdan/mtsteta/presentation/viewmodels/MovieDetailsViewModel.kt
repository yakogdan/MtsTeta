package com.yakogdan.mtsteta.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.interactors.MovieDetailsInteractor
import com.yakogdan.mtsteta.presentation.screenstates.MovieActorsScreenState
import com.yakogdan.mtsteta.presentation.screenstates.MovieDetailsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsInteractor: MovieDetailsInteractor
) : ViewModel() {

    private val _movieDetailsStateFLow =
        MutableStateFlow<MovieDetailsScreenState>(MovieDetailsScreenState.Loading)
    val movieDetailsStateFLow = _movieDetailsStateFLow.asStateFlow()

    private val _movieActorsStateFlow =
        MutableStateFlow<MovieActorsScreenState>(MovieActorsScreenState.Loading)
    val movieActorsStateFlow = _movieActorsStateFlow.asStateFlow()

    private val detailExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _movieDetailsStateFLow.value = MovieDetailsScreenState.Error
    }

    private val actorsExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _movieActorsStateFlow.value = MovieActorsScreenState.Error
    }

    fun getMovieDetailsFromApi(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO + detailExceptionHandler) {
            movieDetailsInteractor.getMovieDetailsFromApi(movieId).collect {
                withContext(Dispatchers.Main) {
                    _movieDetailsStateFLow.value = MovieDetailsScreenState.Result(it)
                }
            }
        }
    }

    fun getMovieActorsFromApi(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO + actorsExceptionHandler) {
            movieDetailsInteractor.getMovieActorsFromApi(movieId).collect {
                withContext(Dispatchers.Main) {
                    _movieActorsStateFlow.value = MovieActorsScreenState.Result(it)
                }
            }
        }
    }
}