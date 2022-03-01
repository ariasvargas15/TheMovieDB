package com.bsav.themoviedb.domain.serie.model

data class Serie(
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    val id: Int,
    val name: String,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)
