package com.randomdroids.moviesinfo.domain

data class MovieDetails(
    var title: String?,
    var director: MovieCredits?,
    var posterPath: String?,
    var releaseDate: String?,
    var duration: String?,
    var description: String?,
    var score: String?
)