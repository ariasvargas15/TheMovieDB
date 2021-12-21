package com.bsav.themoviedb.data.search

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

class SearchRepositoryImplTest {

    private val remoteDataSource = mockk<SearchRemoteDataSource>()
    private lateinit var repository: SearchRepository

    @Before
    fun setUp() {
        repository = SearchRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `when searchProgramByName is called then should call dataSource`() {
        val expected = mockk<List<Program>>()
        every {
            remoteDataSource.searchProgramByName("query")
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.searchProgramByName("query").first()
        }

        verify {
            remoteDataSource.searchProgramByName("query")
        }

        Assert.assertEquals(expected, response)

    }
}