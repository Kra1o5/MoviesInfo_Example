package com.randomdroids.moviesinfo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCreditsDTO(
    @SerializedName("crew") val crew: List<MovieCrewDTO>?
) : Parcelable