package com.bsav.themoviedb.domain

data class Program(
    val backdropPath: String?,
    val id: Int,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)