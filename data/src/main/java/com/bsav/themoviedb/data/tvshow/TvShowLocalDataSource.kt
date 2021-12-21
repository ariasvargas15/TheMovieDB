package com.bsav.themoviedb.data.tvshow

import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowLocalDataSource {
    fun getTvShowById(id: Int): Flow<TvShow>
    suspend fun saveTvShow(tvShow: TvShow)
    fun getPopularTvShows(): Flow<List<TvShow>>
    fun getTopRatedTvShows(): Flow<List<TvShow>>
    suspend fun savePopularTvShows(tvShows: List<TvShow>)
    suspend fun saveTopRatedTvShows(tvShows: List<TvShow>)
    suspend fun deleteTvShowsByType(type: ProgramType.TvShow)
}
