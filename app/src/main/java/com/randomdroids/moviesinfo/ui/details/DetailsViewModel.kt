package com.randomdroids.moviesinfo.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.data.common.ResultData
import com.randomdroids.moviesinfo.di.IoDispatcher
import com.randomdroids.moviesinfo.domain.MovieDetails
import com.randomdroids.moviesinfo.usecases.GetMovieDetailsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @IoDispatcher private val requestDispatcher: CoroutineDispatcher,
    private val getMovieDetailsDataUseCase: GetMovieDetailsDataUseCase
) : ViewModel() {

    companion object {
        private val TAG = DetailsViewModel::class.qualifiedName
    }

    private val _movies = MutableStateFlow<MovieDetails?>(null)
    val movies: StateFlow<MovieDetails?> get() = _movies

    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading


    fun requestMovieDetails(movieId: String) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = getMovieDetailsDataUseCase.invoke(movieId)) {
                is ResultData.Success -> onSuccessGetMovieDetails(result.value)
                is ResultData.Failure -> onErrorGetMovieDetails(result.throwable)
            }
            _loading.value = false
        }
    }

    /**
     * Function to handle when request succeeds
     *
     * @param movieDetails Movie details
     */
    private fun onSuccessGetMovieDetails(movieDetails: Response<MovieDetails>) {
        viewModelScope.launch(requestDispatcher) {
            movieDetails.getValue()?.let { _movies.emit(it) }
        }
    }

    /**
     * Function to handle when request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetMovieDetails(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )
    }
}