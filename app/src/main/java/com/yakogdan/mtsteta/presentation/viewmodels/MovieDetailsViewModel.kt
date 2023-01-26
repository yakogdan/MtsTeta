package com.yakogdan.mtsteta.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.moviedetail.MovieDetailDomain
import com.yakogdan.domain.interactors.MovieDetailsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsInteractor: MovieDetailsInteractor
) : ViewModel() {

    private val _movieDetailLiveData = MutableLiveData<MovieDetailDomain>()
    val movieDetailLiveData get() = _movieDetailLiveData

    fun getMovieDetailFromApi(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movieDetailsInteractor.getMovieDetailFromApi(movieId).collect() {
                    withContext(Dispatchers.Main) {
                        _movieDetailLiveData.value = it
                    }
                }
            } catch (e: Exception) {
                Log.e("eTAG", "getMovieDetailFromApi: Проверь впн", )
            }
        }
    }
}