package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.search.SearchRemoteDataSource
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.framework.network.services.SearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRemoteDataSourceImpl(
    private val service: SearchService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchRemoteDataSource {

    override fun searchProgramByName(query: String): Flow<List<Program>> = flow {
        emit(
            service.searchProgramByName(query = query)
                .mapToDomain()
                .filter {
                    it.type is ProgramType.Movie || it.type is ProgramType.TvShow
                }
        )
    }.flowOn(dispatcher)

}