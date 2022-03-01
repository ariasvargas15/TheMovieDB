package com.bsav.themoviedb.domain.serie.usecase

import com.bsav.themoviedb.domain.serie.model.Serie

interface SerieRepository {
    suspend fun getSeries(): List<Serie>
}