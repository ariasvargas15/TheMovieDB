package com.bsav.themoviedb.data.movie

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override fun getPopularMovies(): Flow<List<Movie>> {
        return flow {
            var response: List<Movie> = emptyList()
            remoteDataSource.getPopularMovies()
                .catch {
                    localDataSource.getPopularMovies()
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

    override fun getTopRatedMovies(): Flow<List<Movie>> {
        return flow {
            var response: List<Movie> = emptyList()
            remoteDataSource.getTopRatedMovies()
                .catch {
                    localDataSource.getTopRatedMovies()
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