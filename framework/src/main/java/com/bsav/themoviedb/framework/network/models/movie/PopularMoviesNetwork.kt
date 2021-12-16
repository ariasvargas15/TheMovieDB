package com.bsav.themoviedb.framework.network.models.movie

import com.bsav.themoviedb.domain.movie.PopularMovies
import com.google.gson.annotations.SerializedName

data class PopularMoviesNetwork(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieNetwork>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
) {
    fun toDomain(): PopularMovies =
        PopularMovies(
            page,
            results.map { it.toDomain() },
            total_pages,
            total_results
        )
}