package com.randomdroids.moviesinfo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieDTOResult(val results: List<MovieDTO>)

@Parcelize
data class MovieDTO(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("id") val id: String?
) : Parcelable