package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.serie.SerieRemoteDataSource
import com.bsav.themoviedb.domain.serie.model.Serie
import com.bsav.themoviedb.framework.network.services.SerieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SerieRemoteDataSourceImpl(private val serieService: SerieService) : SerieRemoteDataSource {
    override suspend fun getSeries(): List<Serie> = withContext(Dispatchers.IO) {
        serieService.getPopularTvShows().mapToDomain()
    }
}