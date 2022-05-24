package com.randomdroids.moviesinfo.data.server.mapper

import com.randomdroids.moviesinfo.data.server.dto.MovieCrewDTO
import com.randomdroids.moviesinfo.domain.MovieCrew

fun MovieCrewDTO.toDomain() = MovieCrew(
    this.job,
    this.name
)