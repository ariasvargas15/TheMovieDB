package com.bsav.themoviedb.data.tvshow

import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.repository.TvShowRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvShowRepositoryImpl(
    private val localDataSource: TvShowLocalDataSource,
    private val remoteDataSource: TvShowRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvShowRepository {

    override fun getPopularTvShows(): Flow<List<TvShow>> {
        return flow {
            var response: List<TvShow> = emptyList()
            remoteDataSource.getPopularTvShows()
                .catch {
                    localDataSource.getPopularTvShows()
                        .flowOn(dispatcher)
                        .collect {
                            response = it
                        }
                }.flowOn(dispatcher)
                .collect {
                    response = it
                }
            emit(response)
        }
    }

    override fun getTopRatedTvShows(): Flow<List<TvShow>> {
        return flow {
            var response: List<TvShow> = emptyList()
            remoteDataSource.getTopRatedTvShows()
                .catch {
                    localDataSource.getTopRatedTvShows()
                        .flowOn(dispatcher)
                        .collect {
                            response = it
                        }
                }.flowOn(dispatcher)
                .collect {
                    response = it
                }
            emit(response)
        }
    }
}