package com.yakogdan.mtsteta.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.domain.entities.MovieGenreDomainEntity
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

    val movieCardLiveData: LiveData<List<MovieCardDomainEntity>> get() = _movieCardLiveData
    private val _movieCardLiveData = MutableLiveData<List<MovieCardDomainEntity>>()

    fun getMovieCards() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieCards().collect {
                withContext(Dispatchers.Main) {
                    _movieCardLiveData.value = it
                }
            }
        }
    }

//    fun getMovieCardsFromRepo() {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.getMovieCardsFromRepo().collect {
//                withContext(Dispatchers.Main) {
//                    _movieCardLiveData.value = it
//                }
//            }
//        }
//    }
//
//    fun getMovieCardsFromDB() {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.getMovieCardsFromDB().collect {
//                withContext(Dispatchers.Main) {
//                    _movieCardLiveData.value = it
//                }
//            }
//        }
//    }
//
//    fun addMovieCard(movieCard: MovieCardDomainEntity) {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.addMovieCard(movieCard)
//        }
//    }
//
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

    private val _movieGenresLiveData = MutableLiveData<List<MovieGenreDomainEntity>>()
    val movieGenresLiveData: LiveData<List<MovieGenreDomainEntity>> get() = _movieGenresLiveData

    fun getMovieGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListInteractor.getMovieGenres().collect {
                withContext(Dispatchers.Main) {
                    _movieGenresLiveData.value = it
                }
            }
        }
    }
//    fun getMovieGenresFromRepo() {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.getMovieGenresFromRepo().collect {
//                withContext(Dispatchers.Main) {
//                    _movieGenresLiveData.value = it
//                }
//            }
//        }
//    }
//
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
//    suspend fun addMovieGenre(movieGenre: MovieGenreDomainEntity) {
//        viewModelScope.launch(Dispatchers.IO) {
//            movieListInteractor.addMovieGenre(movieGenre)
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