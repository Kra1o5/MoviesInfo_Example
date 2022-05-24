package com.randomdroids.moviesinfo.data.server.mapper

import com.randomdroids.moviesinfo.data.server.dto.MovieCreditsDTO
import com.randomdroids.moviesinfo.domain.MovieCredits


fun MovieCreditsDTO.toDomain() = MovieCredits(
    this.crew?.map { it -> it.toDomain() }
)