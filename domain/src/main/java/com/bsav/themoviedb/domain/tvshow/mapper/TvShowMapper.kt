package com.bsav.themoviedb.domain.tvshow.mapper

import com.bsav.themoviedb.domain.Program
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import javax.inject.Inject

class TvShowMapper @Inject constructor() {

    fun tvShowToProgram(tvShow: TvShow): Program {
        with(tvShow) {
            return Program(
                backdropPath,
                id,
                originalName,
                popularity,
                posterPath,
                firstAirDate,
                name,
                voteAverage,
                voteCount
            )
        }
    }
}