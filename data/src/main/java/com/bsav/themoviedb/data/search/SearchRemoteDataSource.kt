package com.bsav.themoviedb.data.search

import com.bsav.themoviedb.domain.program.Program
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    fun searchProgramByName(query: String): Flow<List<Program>>
}