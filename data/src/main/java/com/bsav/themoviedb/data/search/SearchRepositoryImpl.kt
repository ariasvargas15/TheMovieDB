package com.bsav.themoviedb.data.search

import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.search.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchRepository {
    override fun searchProgramByName(query: String): Flow<List<Program>> =
        remoteDataSource.searchProgramByName(query)
            .flowOn(dispatcher)
}