package com.bsav.themoviedb.domain.movie.model

data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val id: Int,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)