package com.randomdroids.moviesinfo.data.server

import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.data.server.mapper.toDomain
import com.randomdroids.moviesinfo.data.source.RemoteDataSource
import com.randomdroids.moviesinfo.domain.Movie
import com.randomdroids.moviesinfo.domain.MovieDetails
import javax.inject.Inject

class MoviesInfoServerDataSource @Inject constructor(
    private val moviesInfoServerService: MoviesInfoServerService
) :
    RemoteDataSource {

    override suspend fun getMovieData(): Response<List<Movie>> {
        return try {
            val response = moviesInfoServerService.getMoviesInfo()
            if (response.isSuccessful) {
                Response(value = response.body()?.results?.map { it.toDomain() } ?: emptyList())
            } else {
                Response(error = Exception("Request was unsuccessful"))
            }
        } catch (exception: Exception) {
            Response(error = exception.cause)
        }
    }

    override suspend fun getMovieDetailsData(id: String): Response<MovieDetails> {
        return try {
            val response = moviesInfoServerService.getMoviesDetailsInfo(id)
            if (response.isSuccessful) {
                Response(value = response.body()?.toDomain())
            } else {
                Response(error = Exception("Request was unsuccessful"))
            }
        } catch (exception: Exception) {
            Response(error = exception.cause)
        }
    }
}