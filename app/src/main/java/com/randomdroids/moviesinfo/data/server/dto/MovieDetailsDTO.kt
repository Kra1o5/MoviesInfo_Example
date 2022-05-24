package com.randomdroids.moviesinfo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailsDTO(
    @SerializedName("title") val title: String?,
    @SerializedName("credits") val director: MovieCreditsDTO?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val duration: String?,
    @SerializedName("overview") val description: String?,
    @SerializedName("vote_average") val score: String?
) : Parcelable