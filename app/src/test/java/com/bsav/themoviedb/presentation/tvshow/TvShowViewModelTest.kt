package com.bsav.themoviedb.presentation.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bsav.themoviedb.MainCoroutineRule
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.usecases.GetTvShowById
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TvShowViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getTvShowById = mockk<GetTvShowById>()
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(getTvShowById)
    }

    @Test
    fun `when getTvShow is called then should modify liveData`() {
        val tvShow = mockk<TvShow>()
        every {
            getTvShowById(1)
        } answers {
            flowOf(tvShow)
        }

        viewModel.getTvShow(1)

        verify { getTvShowById(1) }
        Assert.assertEquals(tvShow, viewModel.tvShow.value)
    }
}