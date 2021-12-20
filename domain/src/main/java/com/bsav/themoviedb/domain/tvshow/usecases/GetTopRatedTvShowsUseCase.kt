package com.bsav.themoviedb.domain.tvshow.usecases

import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.repository.TvShowRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetTopRatedTvShowsUseCase @Inject constructor(private val tvShowRepository: TvShowRepository) {
    operator fun invoke(): Flow<List<TvShow>> = tvShowRepository.getTopRatedTvShows()

}