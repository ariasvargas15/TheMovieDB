package com.bsav.themoviedb.domain.tvshow.mapper

import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import javax.inject.Inject

class TvShowMapper @Inject constructor() {

    fun tvShowToProgram(tvShow: TvShow): Program {
        with(tvShow) {
            return Program(
                id,
                posterPath,
                ProgramType.TvShow
            )
        }
    }
}