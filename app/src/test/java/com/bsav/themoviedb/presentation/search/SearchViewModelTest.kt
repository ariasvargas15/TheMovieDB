package com.bsav.themoviedb.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bsav.themoviedb.MainCoroutineRule
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.search.usecases.SearchProgramByName
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.usecases.GetTvShowById
import com.bsav.themoviedb.presentation.tvshow.TvShowViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val searchProgramByName = mockk<SearchProgramByName>()
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(searchProgramByName)
    }

    @Test
    fun `when searchProgram is called then should modify liveData`() {
        val programs = listOf(mockk<Program>())
        every {
            searchProgramByName("The 100")
        } answers {
            flowOf(programs)
        }

        viewModel.searchProgram("The 100")

        verify { searchProgramByName("The 100") }
        Assert.assertEquals(programs, viewModel.programs.value)
    }
}