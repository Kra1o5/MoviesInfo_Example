package com.randomdroids.moviesinfo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCrewDTO(
    @SerializedName("job") val job: String?,
    @SerializedName("name") val name: String?,
) : Parcelable