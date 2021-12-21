package com.bsav.themoviedb.domain.program

sealed class ProgramType {
    open class Movie : ProgramType() {
        object Popular : Movie()
        object TopRated : Movie()
    }

    open class TvShow : ProgramType() {
        object Popular : TvShow()
        object TopRated : TvShow()
    }

    object Other : ProgramType()

    fun mapToString(): String {
        return when (this) {
            is Movie.Popular -> "MoviePopular"
            is Movie.TopRated -> "MovieTopRated"
            is TvShow.Popular -> "TvShowPopular"
            is TvShow.TopRated -> "TvShowTopRated"
            is Movie -> "Movie"
            is TvShow -> "TvShow"
            is Other -> "Other"
        }
    }
}

fun String.mapStringToProgramType(): ProgramType {
    return when (this) {
        "MoviePopular" -> ProgramType.Movie.Popular
        "MovieTopRated" -> ProgramType.Movie.TopRated
        "TvShowPopular" -> ProgramType.TvShow.Popular
        "TvShowTopRated" -> ProgramType.TvShow.TopRated
        "Movie" -> ProgramType.Movie()
        "TvShow" -> ProgramType.TvShow()
        else -> ProgramType.Other
    }
}