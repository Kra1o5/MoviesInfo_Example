package com.randomdroids.moviesinfo.data.server

import com.randomdroids.moviesinfo.data.server.ServerConstants.API
import com.randomdroids.moviesinfo.data.server.ServerConstants.APPEND
import com.randomdroids.moviesinfo.data.server.ServerConstants.CREDITS
import com.randomdroids.moviesinfo.data.server.ServerConstants.LANGUAGE
import com.randomdroids.moviesinfo.data.server.ServerConstants.POPULAR
import com.randomdroids.moviesinfo.data.server.ServerConstants.REGION
import com.randomdroids.moviesinfo.data.server.ServerConstants.TYPE
import com.randomdroids.moviesinfo.data.server.dto.MovieDTOResult
import com.randomdroids.moviesinfo.data.server.dto.MovieDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesInfoServerService {
    @GET("$TYPE$POPULAR$API$API_KEY$LANGUAGE$REGION")
    suspend fun getMoviesInfo(): Response<MovieDTOResult>

    @GET("$TYPE{id}$API$API_KEY$LANGUAGE$REGION$APPEND$CREDITS")
    suspend fun getMoviesDetailsInfo(@Path("id") id: String): Response<MovieDetailsDTO>
}