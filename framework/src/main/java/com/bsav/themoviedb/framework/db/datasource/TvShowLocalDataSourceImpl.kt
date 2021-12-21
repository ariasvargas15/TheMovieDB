package com.bsav.themoviedb.framework.db.datasource

import com.bsav.themoviedb.data.tvshow.TvShowLocalDataSource
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TvShowLocalDataSourceImpl : TvShowLocalDataSource {

    override fun getPopularTvShows(): Flow<List<TvShow>> {
        return flowOf(emptyList())
    }

    override fun getTopRatedTvShows(): Flow<List<TvShow>> {
        return flowOf(emptyList())
    }

    override suspend fun savePopularTvShows() {
        TODO("Not yet implemented")
    }

    override suspend fun saveTopRatedTvShows() {
        TODO("Not yet implemented")
    }
}