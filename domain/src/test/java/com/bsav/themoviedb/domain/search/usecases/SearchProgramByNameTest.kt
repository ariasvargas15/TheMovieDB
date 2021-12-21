package com.bsav.themoviedb.domain.search.usecases

import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.search.repository.SearchRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchProgramByNameTest {

    private val searchRepository = mockk<SearchRepository>()
    private lateinit var searchProgramByName: SearchProgramByName

    @Before
    fun setUp() {
        searchProgramByName = SearchProgramByName(searchRepository)
    }

    @Test
    fun `when invoke is called then should call repository and return its answer`() {
        val expected = listOf(mockk<Program>())

        every {
            searchRepository.searchProgramByName("Iron Man")
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            searchProgramByName("Iron Man").first()
        }

        verify(exactly = 1) {
            searchRepository.searchProgramByName("Iron Man")
        }
        Assert.assertEquals(expected, response)
    }

}