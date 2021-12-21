package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.tvshow.TvShowRemoteDataSource
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.framework.network.services.TvShowService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TvShowRemoteDataSourceImpl(private val service: TvShowService) : TvShowRemoteDataSource {

    override fun getPopularTvShows(): Flow<List<TvShow>> {
        return flow {
            emit(service.getPopularTvShows().mapToDomain())
        }
    }

    override fun getTopRatedTvShows(): Flow<List<TvShow>> {
        return flow {
            emit(service.getTopRatedTvShows().mapToDomain())
        }
    }
}