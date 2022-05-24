package com.randomdroids.moviesinfo.usecases

import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.data.common.ResultData
import com.randomdroids.moviesinfo.data.repository.MoviesInfoRepository
import com.randomdroids.moviesinfo.domain.Movie

class GetMovieDataUseCase(private val moviesInfoRepository: MoviesInfoRepository) {
    suspend fun invoke(): ResultData<Response<List<Movie>>> = moviesInfoRepository.getMovieData()
}