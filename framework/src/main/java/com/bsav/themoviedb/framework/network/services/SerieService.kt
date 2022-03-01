package com.bsav.themoviedb.framework.network.services

import com.bsav.themoviedb.framework.network.API_KEY
import com.bsav.themoviedb.framework.network.models.serie.PageSerieNetwork
import com.bsav.themoviedb.framework.network.models.tvshow.PageTvShowNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface SerieService {
    @GET(ENDPOINT_POPULAR)
    suspend fun getPopularTvShows(
        @Query(PAGE) page: Int = FIRST_PAGE,
        @Query(KEY) apiKey: String = API_KEY
    ): PageSerieNetwork
}

private const val PAGE = "page"
private const val KEY = "api_key"
private const val FIRST_PAGE = 1
private const val ENDPOINT_POPULAR = "tv/popular"