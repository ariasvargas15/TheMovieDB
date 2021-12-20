package com.bsav.themoviedb.domain.tvshow.repository

import com.bsav.themoviedb.domain.tvshow.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getPopularTvShows(): Flow<List<TvShow>>
    fun getTopRatedTvShows(): Flow<List<TvShow>>
}