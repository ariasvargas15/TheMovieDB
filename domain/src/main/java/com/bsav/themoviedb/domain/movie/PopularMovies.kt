package com.bsav.themoviedb.domain.movie

data class PopularMovies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)