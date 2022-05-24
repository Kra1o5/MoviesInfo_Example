package com.randomdroids.moviesinfo.data.server

import com.randomdroids.moviesinfo.data.server.ServerConstants.API
import com.randomdroids.moviesinfo.data.server.ServerConstants.LANGUAGE
import com.randomdroids.moviesinfo.data.server.ServerConstants.POPULAR
import com.randomdroids.moviesinfo.data.server.ServerConstants.REGION
import com.randomdroids.moviesinfo.data.server.ServerConstants.TYPE
import com.randomdroids.moviesinfo.data.server.dto.MovieDTOResult
import retrofit2.Response
import retrofit2.http.GET

interface MoviesInfoServerService {
    @GET("$TYPE$POPULAR$API$API_KEY$LANGUAGE$REGION")
    suspend fun getMoviesInfo(): Response<MovieDTOResult>
}