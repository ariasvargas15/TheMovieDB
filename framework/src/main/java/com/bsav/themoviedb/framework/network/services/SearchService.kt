package com.bsav.themoviedb.framework.network.services

import com.bsav.themoviedb.framework.network.API_KEY
import com.bsav.themoviedb.framework.network.models.search.PageSearchNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(ENDPOINT)
    suspend fun searchProgramByName(
        @Query(PAGE) page: Int = FIRST_PAGE,
        @Query(KEY) apiKey: String = API_KEY,
        @Query(QUERY) query: String
    ): PageSearchNetwork

}

private const val ENDPOINT = "search/multi"
private const val PAGE = "page"
private const val KEY = "api_key"
private const val QUERY = "query"
private const val FIRST_PAGE = 1

