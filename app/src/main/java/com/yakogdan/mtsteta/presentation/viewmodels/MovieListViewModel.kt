package com.yakogdan.mtsteta.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.MovieCardDomain
import com.yakogdan.domain.entities.MovieGenreDomain
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

    // MovieCard

    private val _movieCardLiveData = MutableLiveData<List<MovieCardDomain>>()
    val movieCardLiveData: LiveData<List<MovieCardDomain>> get() = _movieCardLiveData

//    fun getMovieCards() {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.getMovieCards().collect {
//                withContext(Dispatchers.Main) {
//                    _movieCardLiveData.value = it
//                }
//            }
//        }
//    }

    fun getMovieCardsFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movieListInteractor.getMovieCardsFromApi().collect() {
                    withContext(Dispatchers.Main) {
                        _movieCardLiveData.value = it
                    }
                }
            } catch (e: Exception) {
                Log.e("eTAG", "getMovieCardsFromApi: не работает ниче")
            }
        }
    }

    fun getMovieCardsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieCardsFromDB().collect {
                withContext(Dispatchers.Main) {
                    _movieCardLiveData.value = it
                }
            }
        }
    }

//    fun addMovieCards(movieCards: List<MovieCardDomainEntity>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.addMovieCards(movieCards)
//        }
//    }
//
//    fun clearMovieCardsDB() {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.clearMovieCardsDB()
//        }
//    }

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

//    fun getMovieGenresFromDB() {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.getMovieGenresFromDB().collect {
//                withContext(Dispatchers.Main) {
//                    _movieGenresLiveData.value = it
//                }
//            }
//        }
//    }
//
//    suspend fun addMovieGenres(movieGenres: List<MovieGenreDomainEntity>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.addMovieGenres(movieGenres)
//        }
//    }
//
//    fun clearMovieGenresDB() {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.clearMovieGenresDB()
//        }
//    }
}