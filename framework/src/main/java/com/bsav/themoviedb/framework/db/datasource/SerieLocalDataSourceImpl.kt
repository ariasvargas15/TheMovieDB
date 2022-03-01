package com.bsav.themoviedb.framework.db.datasource

import com.bsav.themoviedb.data.serie.SerieLocalDataSource
import com.bsav.themoviedb.domain.serie.model.Serie
import com.bsav.themoviedb.framework.db.daos.SerieDao
import com.bsav.themoviedb.framework.db.entities.SerieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SerieLocalDataSourceImpl (private val serieDao: SerieDao) : SerieLocalDataSource {
    override suspend fun getSeries(): List<Serie> = withContext(Dispatchers.IO) {
        serieDao.getSeries().map { it.mapToDomain() }
    }


    override suspend fun saveSeries(series: List<Serie>) = withContext(Dispatchers.IO) {
        serieDao.saveSeries(series.map { SerieEntity(it) })
    }
}