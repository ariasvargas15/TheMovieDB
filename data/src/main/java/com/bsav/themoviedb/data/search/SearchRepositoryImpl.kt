package com.bsav.themoviedb.data.search

import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource,
) : SearchRepository {

    override fun searchProgramByName(query: String): Flow<List<Program>> =
        remoteDataSource.searchProgramByName(query)

}