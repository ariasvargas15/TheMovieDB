package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.tvshow.TvShowRemoteDataSource
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.framework.network.services.TvShowService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvShowRemoteDataSourceImpl(
    private val service: TvShowService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvShowRemoteDataSource {

    override fun getTvShowById(id: Int): Flow<TvShow> = flow {
        emit(service.getTvShowById(id).mapTpDomain())
    }.flowOn(dispatcher)

    override fun getPopularTvShows(): Flow<List<TvShow>> = flow {
        emit(service.getPopularTvShows().mapToDomain())
    }.flowOn(dispatcher)


    override fun getTopRatedTvShows(): Flow<List<TvShow>> = flow {
        emit(service.getTopRatedTvShows().mapToDomain())
    }.flowOn(dispatcher)

}