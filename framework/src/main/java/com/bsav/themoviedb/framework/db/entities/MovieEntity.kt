package com.bsav.themoviedb.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsav.themoviedb.domain.movie.model.Movie

@Entity
data class MovieEntity(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    @PrimaryKey val id: Int,
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
) {
    constructor(movie: Movie) : this(
        movie.adult,
        movie.backdropPath,
        movie.id,
        movie.originalLanguage,
        movie.originalTitle,
        movie.overview,
        movie.popularity,
        movie.posterPath,
        movie.releaseDate,
        movie.title
    )

    fun mapToDomain() = Movie(
        adult,
        backdropPath,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title
    )

}