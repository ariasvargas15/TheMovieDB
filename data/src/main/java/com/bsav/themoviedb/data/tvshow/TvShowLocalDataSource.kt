package com.bsav.themoviedb.data.tvshow

import com.bsav.themoviedb.domain.tvshow.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowLocalDataSource {
    fun getPopularTvShows(): Flow<List<TvShow>>
    fun getTopRatedTvShows(): Flow<List<TvShow>>
    suspend fun savePopularTvShows()
    suspend fun saveTopRatedTvShows()
}
