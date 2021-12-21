package com.bsav.themoviedb.framework.db.datasource

import com.bsav.themoviedb.data.movie.MovieLocalDataSource
import com.bsav.themoviedb.domain.movie.mapper.MovieMapper
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.framework.db.daos.ProgramDao
import com.bsav.themoviedb.framework.db.entities.ProgramEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MovieLocalDataSourceImpl(
    private val dao: ProgramDao,
    private val movieMapper: MovieMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieLocalDataSource {

    override fun getPopularMovies(): Flow<List<Movie>> = flow {
        emit(
            dao.getProgramsByType(
                ProgramType.Movie.Popular.mapToString()
            ).map {
                movieMapper.programToMovie(it.mapToDomain())
            }
        )
    }.flowOn(dispatcher)


    override fun getTopRatedMovies(): Flow<List<Movie>> = flow {
        emit(
            dao.getProgramsByType(
                ProgramType.Movie.TopRated.mapToString()
            ).map {
                movieMapper.programToMovie(it.mapToDomain())
            }
        )
    }.flowOn(dispatcher)

    override suspend fun savePopularMovies(movies: List<Movie>) {
        withContext(dispatcher) {
            dao.saveProgramList(
                movies.map {
                    ProgramEntity(
                        movieMapper.movieToProgram(it, ProgramType.Movie.Popular)
                    )
                }
            )
        }
    }

    override suspend fun saveTopRatedMovies(movies: List<Movie>) {
        withContext(dispatcher) {
            dao.saveProgramList(
                movies.map {
                    ProgramEntity(
                        movieMapper.movieToProgram(it, ProgramType.Movie.TopRated)
                    )
                }
            )
        }
    }

    override suspend fun deleteMoviesByType(type: ProgramType.Movie) {
        withContext(dispatcher) {
            dao.deleteProgramsByType(type.mapToString())
        }
    }


}