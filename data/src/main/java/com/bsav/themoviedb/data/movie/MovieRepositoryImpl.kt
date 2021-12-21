package com.bsav.themoviedb.data.movie

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.repository.MovieRepository
import com.bsav.themoviedb.domain.program.ProgramType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getPopularMovies(): Flow<List<Movie>> {
        return flow {
            var response: List<Movie> = emptyList()
            remoteDataSource.getPopularMovies()
                .catch {
                    localDataSource.getPopularMovies()
                        .collect {
                            response = it
                        }
                }.collect {
                    localDataSource.deleteMoviesByType(ProgramType.Movie.Popular)
                    localDataSource.savePopularMovies(it)
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
                        .collect {
                            response = it
                        }
                }.collect {
                    localDataSource.deleteMoviesByType(ProgramType.Movie.TopRated)
                    localDataSource.saveTopRatedMovies(it)
                    response = it
                }
            emit(response)
        }
    }
}