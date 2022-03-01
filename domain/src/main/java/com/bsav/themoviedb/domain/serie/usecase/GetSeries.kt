package com.bsav.themoviedb.domain.serie.usecase

import com.bsav.themoviedb.domain.serie.model.Serie
import javax.inject.Inject

class GetSeries @Inject constructor(private val serieRepository: SerieRepository) {
    suspend operator fun invoke(): List<Serie> = serieRepository.getSeries()
}