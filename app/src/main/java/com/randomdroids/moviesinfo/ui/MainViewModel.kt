package com.randomdroids.moviesinfo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.data.common.ResultData
import com.randomdroids.moviesinfo.di.IoDispatcher
import com.randomdroids.moviesinfo.domain.Movie
import com.randomdroids.moviesinfo.usecases.GetMovieDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @IoDispatcher private val requestDispatcher: CoroutineDispatcher,
    private val getMovieDataUseCase: GetMovieDataUseCase
) : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.qualifiedName
    }

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading


    fun requestMoviesList() {
        viewModelScope.launch {
            _loading.value = true
            when (val result = getMovieDataUseCase.invoke()) {
                is ResultData.Success -> onSuccessGetMovies(result.value)
                is ResultData.Failure -> onErrorGetMovies(result.throwable)
            }
            _loading.value = false
        }
    }

    /**
     * Function to handle when request succeeds
     *
     * @param movieDetails List of movies
     */
    private fun onSuccessGetMovies(movieDetails: Response<List<Movie>>) {
        viewModelScope.launch(requestDispatcher) {
            movieDetails.getValue()?.let { _movies.emit(it) }
        }
    }

    /**
     * Function to handle when request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetMovies(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )
    }
}