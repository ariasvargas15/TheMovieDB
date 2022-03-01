package com.bsav.themoviedb.data.serie

import com.bsav.themoviedb.domain.serie.model.Serie

interface SerieLocalDataSource {
    suspend fun getSeries(): List<Serie>
    suspend fun saveSeries(series: List<Serie>)
}