package com.bsav.themoviedb.data.tvshow

import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class TvShowRepositoryImpl(
    private val localDataSource: TvShowLocalDataSource,
    private val remoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {

    override fun getTvShowById(id: Int): Flow<TvShow> {
        return flow {
            remoteDataSource.getTvShowById(id)
                .catch {
                    localDataSource.getTvShowById(id)
                        .collect {
                            emit(it)
                        }
                }.collect {
                    localDataSource.saveTvShow(it)
                    emit(it)
                }
        }
    }

    override fun getPopularTvShows(): Flow<List<TvShow>> {
        return flow {
            var response: List<TvShow> = emptyList()
            remoteDataSource.getPopularTvShows()
                .catch {
                    localDataSource.getPopularTvShows()
                        .collect {
                            response = it
                        }
                }.collect {
                    localDataSource.deleteTvShowsByType(ProgramType.TvShow.Popular)
                    localDataSource.savePopularTvShows(it)
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
                        .collect {
                            response = it
                        }
                }.collect {
                    localDataSource.deleteTvShowsByType(ProgramType.TvShow.TopRated)
                    localDataSource.saveTopRatedTvShows(it)
                    response = it
                }
            emit(response)
        }
    }
}