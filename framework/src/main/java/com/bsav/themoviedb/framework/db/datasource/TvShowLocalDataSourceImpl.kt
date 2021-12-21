package com.bsav.themoviedb.framework.db.datasource

import com.bsav.themoviedb.data.tvshow.TvShowLocalDataSource
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.domain.tvshow.mapper.TvShowMapper
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.framework.db.daos.ProgramDao
import com.bsav.themoviedb.framework.db.daos.TvShowDao
import com.bsav.themoviedb.framework.db.entities.ProgramEntity
import com.bsav.themoviedb.framework.db.entities.TvShowEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class TvShowLocalDataSourceImpl(
    private val programDao: ProgramDao,
    private val tvShowDao: TvShowDao,
    private val tvShowMapper: TvShowMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvShowLocalDataSource {

    override fun getTvShowById(id: Int): Flow<TvShow> = flow {
        emit(
            tvShowDao.getTvShowById(id).mapToDomain()
        )
    }.flowOn(dispatcher)

    override suspend fun saveTvShow(tvShow: TvShow) {
        withContext(dispatcher) {
            tvShowDao.saveTvShow(TvShowEntity(tvShow))
        }
    }

    override fun getPopularTvShows(): Flow<List<TvShow>> = flow {
        emit(
            programDao.getProgramsByType(
                ProgramType.TvShow.Popular.mapToString()
            ).map {
                tvShowMapper.programToTvShow(it.mapToDomain())
            }
        )
    }.flowOn(dispatcher)

    override fun getTopRatedTvShows(): Flow<List<TvShow>> = flow {
        emit(
            programDao.getProgramsByType(
                ProgramType.TvShow.TopRated.mapToString()
            ).map {
                tvShowMapper.programToTvShow(it.mapToDomain())
            }
        )
    }.flowOn(dispatcher)

    override suspend fun savePopularTvShows(tvShows: List<TvShow>) {
        withContext(dispatcher) {
            programDao.saveProgramList(
                tvShows.map {
                    ProgramEntity(
                        tvShowMapper.tvShowToProgram(it, ProgramType.TvShow.Popular)
                    )
                }
            )
        }
    }

    override suspend fun saveTopRatedTvShows(tvShows: List<TvShow>) {
        withContext(dispatcher) {
            programDao.saveProgramList(
                tvShows.map {
                    ProgramEntity(
                        tvShowMapper.tvShowToProgram(it, ProgramType.TvShow.TopRated)
                    )
                }
            )
        }
    }

    override suspend fun deleteTvShowsByType(type: ProgramType.TvShow) {
        withContext(dispatcher) {
            programDao.deleteProgramsByType(type.mapToString())
        }
    }
}