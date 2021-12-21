package com.bsav.themoviedb.domain.search.usecases

import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.search.repository.SearchRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchProgramByName @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(query: String): Flow<List<Program>> = searchRepository.searchProgramByName(query)
}