package com.bsav.themoviedb.data.serie

import com.bsav.themoviedb.domain.serie.model.Serie

interface SerieRemoteDataSource {
    suspend fun getSeries(): List<Serie>
}