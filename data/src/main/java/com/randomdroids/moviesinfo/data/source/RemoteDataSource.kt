package com.randomdroids.moviesinfo.data.source

import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.domain.Movie


interface RemoteDataSource {
    suspend fun getMovieData(): Response<List<Movie>>
}