package com.bsav.themoviedb.framework.network.services

import com.bsav.themoviedb.framework.network.API_KEY
import com.bsav.themoviedb.framework.network.models.movie.PopularMoviesNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesService {

    @GET(ENDPOINT)
    suspend fun getPopularMovies(
        @Query(PAGE) page: Int,
        @Query(KEY) apiKey: String = API_KEY
    ): PopularMoviesNetwork

}

private const val ENDPOINT = "movie/popular"
private const val PAGE = "page"
private const val KEY = "api_key"

