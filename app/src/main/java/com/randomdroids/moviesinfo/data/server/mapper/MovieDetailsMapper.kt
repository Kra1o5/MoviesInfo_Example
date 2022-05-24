package com.randomdroids.moviesinfo.data.server.mapper

import com.randomdroids.moviesinfo.data.server.dto.MovieDetailsDTO
import com.randomdroids.moviesinfo.domain.MovieDetails


fun MovieDetailsDTO.toDomain() = MovieDetails(
    this.title,
    this.director?.toDomain(),
    this.posterPath,
    this.releaseDate,
    this.duration,
    this.description,
    this.score
)