package com.bsav.themoviedb.framework.network.models.movie

import com.bsav.themoviedb.domain.movie.model.Movie
import com.google.gson.annotations.SerializedName

data class PageMovieNetwork(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieNetwork>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    fun mapToDomain(): List<Movie> = results.map { it.mapToDomain() }
}