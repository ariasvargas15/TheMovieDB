package com.bsav.themoviedb.framework.network.models.search

import com.bsav.themoviedb.domain.program.Program
import com.google.gson.annotations.SerializedName

data class PageSearchNetwork(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<SearchNetwork>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    fun mapToDomain(): List<Program> = results.map { it.mapToDomain() }
}