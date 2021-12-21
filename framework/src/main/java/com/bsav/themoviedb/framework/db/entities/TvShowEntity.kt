package com.bsav.themoviedb.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsav.themoviedb.domain.tvshow.model.TvShow

@Entity
data class TvShowEntity(
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    @PrimaryKey val id: Int,
    val name: String,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
) {
    constructor(tvShow: TvShow) : this(
        tvShow.backdropPath,
        tvShow.firstAirDate,
        tvShow.id,
        tvShow.name,
        tvShow.originalLanguage,
        tvShow.originalName,
        tvShow.overview,
        tvShow.popularity,
        tvShow.posterPath,
        tvShow.voteAverage,
        tvShow.voteCount
    )

    fun mapToDomain() = TvShow(
        backdropPath,
        firstAirDate,
        id,
        name,
        originalLanguage,
        originalName,
        overview,
        popularity,
        posterPath,
        voteAverage,
        voteCount
    )
}