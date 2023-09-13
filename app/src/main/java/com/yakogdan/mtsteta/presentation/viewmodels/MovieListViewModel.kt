package com.yakogdan.mtsteta.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.interactors.MovieListInteractor
import com.yakogdan.mtsteta.presentation.screenstates.MovieListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListInteractor: MovieListInteractor
) : ViewModel() {

    // MovieCard

    private val _movieListScreenStateLD = MutableLiveData<MovieListScreenState>()
    val movieListScreenStateLD: LiveData<MovieListScreenState> get() = _movieListScreenStateLD

    fun getMovieCards() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieCards().collect {
                withContext(Dispatchers.Main) {
                    _movieListScreenStateLD.value = MovieListScreenState.Loading
                    delay(5000)
                    if (it.isEmpty()) {
                        _movieListScreenStateLD.value = MovieListScreenState.Error
                    } else {
                        _movieListScreenStateLD.value = MovieListScreenState.Result(it)
                    }
                }
            }
        }
    }

    // MovieGenre

    private val _movieGenresLiveData = MutableLiveData<List<MovieGenreDomain>>()
    val movieGenresLiveData: LiveData<List<MovieGenreDomain>> get() = _movieGenresLiveData

    fun getMovieGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieGenres().collect {
                withContext(Dispatchers.Main) {
                    _movieGenresLiveData.value = it
                }
            }
        }
    }
}