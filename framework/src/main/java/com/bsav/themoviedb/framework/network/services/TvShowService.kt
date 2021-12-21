package com.bsav.themoviedb.framework.network.services

import com.bsav.themoviedb.framework.network.API_KEY
import com.bsav.themoviedb.framework.network.models.tvshow.PageTvShowNetwork
import com.bsav.themoviedb.framework.network.models.tvshow.TvShowNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET(ENDPOINT_POPULAR)
    suspend fun getPopularTvShows(
        @Query(PAGE) page: Int = FIRST_PAGE,
        @Query(KEY) apiKey: String = API_KEY
    ): PageTvShowNetwork

    @GET(ENDPOINT_TOP_RATED)
    suspend fun getTopRatedTvShows(
        @Query(PAGE) page: Int = FIRST_PAGE,
        @Query(KEY) apiKey: String = API_KEY
    ): PageTvShowNetwork

    @GET(ENDPOINT_TV_SHOW_DETAIL)
    suspend fun getTvShowById(
        @Path(ID) id: Int,
        @Query(KEY) apiKey: String = API_KEY
    ): TvShowNetwork

}

private const val ID = "id"
private const val PAGE = "page"
private const val KEY = "api_key"
private const val FIRST_PAGE = 1
private const val ENDPOINT_POPULAR = "tv/popular"
private const val ENDPOINT_TOP_RATED = "tv/top_rated"
private const val ENDPOINT_TV_SHOW_DETAIL = "tv/{$ID}"

