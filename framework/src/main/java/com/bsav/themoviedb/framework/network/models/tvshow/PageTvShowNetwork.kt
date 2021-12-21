package com.bsav.themoviedb.framework.network.models.tvshow

import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.google.gson.annotations.SerializedName

data class PageTvShowNetwork(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<TvShowNetwork>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    fun mapToDomain(): List<TvShow> = results.map { it.mapTpDomain() }
}