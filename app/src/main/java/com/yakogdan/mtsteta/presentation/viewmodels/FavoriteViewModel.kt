package com.yakogdan.mtsteta.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.interactors.FavoriteInteractor
import com.yakogdan.mtsteta.presentation.screenstates.FavoriteScreenState
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
class FavoriteViewModel @Inject constructor(
    private val favoriteInteractor: FavoriteInteractor
) : ViewModel() {


    private val _favoriteStateFlow =
        MutableStateFlow<FavoriteScreenState>(FavoriteScreenState.Loading)
    val favoriteStateFlow = _favoriteStateFlow.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _favoriteStateFlow.value = FavoriteScreenState.Error
    }

    fun getMovieCards() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            if (favoriteInteractor.movieCardsDbIsEmpty()) {
                _favoriteStateFlow.value = FavoriteScreenState.Empty
            } else {
                favoriteInteractor.getMovieCardsFromDB()
                    .catch { _favoriteStateFlow.value = FavoriteScreenState.Error }
                    .onStart { _favoriteStateFlow.value = FavoriteScreenState.Loading }
                    .collect { list ->
                        if (list.isEmpty()) {
                            _favoriteStateFlow.value = FavoriteScreenState.Empty
                        } else {
                            _favoriteStateFlow.value = FavoriteScreenState.Result(list)
                        }
                    }
            }
        }
    }

    fun deleteMovieCard(movieCard: MovieCardDomain) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            favoriteInteractor.deleteMovieCard(movieCard)
        }
    }
}