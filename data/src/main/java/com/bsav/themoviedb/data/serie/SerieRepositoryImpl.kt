package com.bsav.themoviedb.data.serie

import com.bsav.themoviedb.domain.serie.model.Serie
import com.bsav.themoviedb.domain.serie.usecase.SerieRepository
import javax.inject.Inject

class SerieRepositoryImpl @Inject constructor(
    private val serieRemoteDataSource: SerieRemoteDataSource,
    private val serieLocalDataSource: SerieLocalDataSource
) : SerieRepository {

    override suspend fun getSeries(): List<Serie> {
        return try {
            val series = serieRemoteDataSource.getSeries()
            serieLocalDataSource.saveSeries(series)
            series
        } catch (e: Exception) {
            serieLocalDataSource.getSeries()
        }
    }
}