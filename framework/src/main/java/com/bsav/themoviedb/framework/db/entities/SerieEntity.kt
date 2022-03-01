package com.bsav.themoviedb.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsav.themoviedb.domain.serie.model.Serie

@Entity
data class SerieEntity(
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
    constructor(serie: Serie) : this(
        serie.backdropPath,
        serie.firstAirDate,
        serie.id,
        serie.name,
        serie.originalLanguage,
        serie.originalName,
        serie.overview,
        serie.popularity,
        serie.posterPath,
        serie.voteAverage,
        serie.voteCount
    )

    fun mapToDomain() = Serie(
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