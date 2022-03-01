package com.bsav.themoviedb.framework.network.models.serie

import com.bsav.themoviedb.domain.serie.model.Serie
import com.google.gson.annotations.SerializedName

data class PageSerieNetwork(
    @SerializedName("results") val results: List<SerieNetwork>
) {
    fun mapToDomain(): List<Serie> = results.map { it.mapToDomain() }
}