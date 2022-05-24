package com.randomdroids.moviesinfo.data.server.mapper

import com.randomdroids.moviesinfo.data.server.dto.MovieDTO
import com.randomdroids.moviesinfo.domain.Movie

fun MovieDTO.toDomain() = Movie(
    this.posterPath,
    this.title,
    this.releaseDate,
    this.id
)