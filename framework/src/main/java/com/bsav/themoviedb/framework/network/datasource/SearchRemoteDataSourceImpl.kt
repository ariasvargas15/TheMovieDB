package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.search.SearchRemoteDataSource
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.framework.network.services.SearchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRemoteDataSourceImpl(private val service: SearchService) : SearchRemoteDataSource {
    override fun searchProgramByName(query: String): Flow<List<Program>> {
        return flow {
            emit(
                service.searchProgramByName(query = query)
                    .mapToDomain()
                    .filter {
                        it.type is ProgramType.Movie || it.type is ProgramType.TvShow
                    }
            )
        }
    }
}