package com.randomdroids.moviesinfo.usecases

import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.data.common.ResultData
import com.randomdroids.moviesinfo.data.repository.MoviesInfoRepository
import com.randomdroids.moviesinfo.domain.MovieDetails

class GetMovieDetailsDataUseCase(private val moviesInfoRepository: MoviesInfoRepository) {
    suspend fun invoke(id: String): ResultData<Response<MovieDetails>> = moviesInfoRepository.getMovieDetailsData(id)
}