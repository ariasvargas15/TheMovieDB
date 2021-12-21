package com.bsav.themoviedb.domain.search.repository

import com.bsav.themoviedb.domain.program.Program
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchProgramByName(query: String): Flow<List<Program>>
}