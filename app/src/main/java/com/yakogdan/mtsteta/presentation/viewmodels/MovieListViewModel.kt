package com.yakogdan.mtsteta.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.domain.entities.MovieGenresDomainEntity
import com.yakogdan.domain.interactors.MovieListInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListInteractor: MovieListInteractor
) : ViewModel() {

    private val _movieCardLiveData = MutableLiveData<List<MovieCardDomainEntity>>()
    val movieCardLiveData: LiveData<List<MovieCardDomainEntity>> get() = _movieCardLiveData

    private val _movieGenresLiveData = MutableLiveData<List<MovieGenresDomainEntity>>()
    val movieGenresLiveData: LiveData<List<MovieGenresDomainEntity>> get() = _movieGenresLiveData


    fun getMovieCards() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieCards().collect() {
                withContext(Dispatchers.Main) {
                    _movieCardLiveData.value = it
                }
            }
        }
    }

    fun getMovieGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieGenres().collect() {
                withContext(Dispatchers.Main) {
                    _movieGenresLiveData.value = it
                }
            }
        }
    }
}