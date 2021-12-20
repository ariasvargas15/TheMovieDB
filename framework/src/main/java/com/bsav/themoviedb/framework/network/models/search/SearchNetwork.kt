package com.bsav.themoviedb.framework.network.models.search

import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.google.gson.annotations.SerializedName

data class SearchNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("media_type") val mediaType: String,
) {

    fun mapToDomain(): Program = Program(
        id,
        posterPath,
        convertToType(mediaType)
    )

    private fun convertToType(type: String): ProgramType {
        return when (type) {
            "movie" -> ProgramType.Movie
            "tv" -> ProgramType.TvShow
            else -> ProgramType.Other
        }
    }
}
