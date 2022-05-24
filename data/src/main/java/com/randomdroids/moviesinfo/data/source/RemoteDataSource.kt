package com.randomdroids.moviesinfo.data.source

import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.domain.Movie
import com.randomdroids.moviesinfo.domain.MovieDetails


interface RemoteDataSource {
    suspend fun getMovieData(): Response<List<Movie>>
    suspend fun getMovieDetailsData(id: String): Response<MovieDetails>
}