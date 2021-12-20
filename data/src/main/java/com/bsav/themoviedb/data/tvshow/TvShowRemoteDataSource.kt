package com.bsav.themoviedb.data.tvshow

import com.bsav.themoviedb.domain.tvshow.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRemoteDataSource {
    fun getPopularTvShows(): Flow<List<TvShow>>
    fun getTopRatedTvShows(): Flow<List<TvShow>>
}
