package com.randomdroids.moviesinfo.data.repository

import com.randomdroids.moviesinfo.data.common.Response
import com.randomdroids.moviesinfo.data.common.ResultData
import com.randomdroids.moviesinfo.data.source.RemoteDataSource
import com.randomdroids.moviesinfo.domain.Movie

class MoviesInfoRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getMovieData(): ResultData<Response<List<Movie>>> {
        return try {
            ResultData.Success(remoteDataSource.getMovieData())
        } catch (exception: Exception) {
            ResultData.Failure(exception)
        }
    }
}