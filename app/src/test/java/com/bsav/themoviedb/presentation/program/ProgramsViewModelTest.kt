package com.bsav.themoviedb.presentation.program

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bsav.themoviedb.MainCoroutineRule
import com.bsav.themoviedb.domain.movie.mapper.MovieMapper
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.usecases.GetPopularMovies
import com.bsav.themoviedb.domain.movie.usecases.GetTopRatedMovies
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.tvshow.mapper.TvShowMapper
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.usecases.GetPopularTvShows
import com.bsav.themoviedb.domain.tvshow.usecases.GetTopRatedTvShows
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
class ProgramsViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getPopularMoviesUseCase = mockk<GetPopularMovies>()
    private val getTopRatedMoviesUseCase = mockk<GetTopRatedMovies>()
    private val getPopularTvShowsUseCase = mockk<GetPopularTvShows>()
    private val getTopRatedTvShowsUseCase = mockk<GetTopRatedTvShows>()
    private val movieMapper = mockk<MovieMapper>()
    private val tvShowMapper = mockk<TvShowMapper>()
    private lateinit var viewModel: ProgramsViewModel

    private val program = mockk<Program>()
    private val programs = listOf(program)
    private val movie = mockk<Movie>()
    private val movies = listOf(movie)
    private val tvShow = mockk<TvShow>()
    private val tvShows = listOf(tvShow)

    @Before
    fun setUp() {
        viewModel = ProgramsViewModel(
            getPopularMoviesUseCase,
            getTopRatedMoviesUseCase,
            getPopularTvShowsUseCase,
            getTopRatedTvShowsUseCase,
            movieMapper,
            tvShowMapper
        )
    }

    init {
        every { movieMapper.movieToProgram(any(), any()) } answers { program }
        every { tvShowMapper.tvShowToProgram(any(), any()) } answers { program }
        every { getPopularMoviesUseCase() } answers { flowOf(movies) }
        every { getTopRatedMoviesUseCase() } answers { flowOf(movies) }
        every { getPopularTvShowsUseCase() } answers { flowOf(tvShows) }
        every { getTopRatedTvShowsUseCase() } answers { flowOf(tvShows) }
    }

    @Test
    fun `when getMovies is called then should call useCases and modify liveData`() {
        viewModel.getMovies()

        verify {
            getPopularMoviesUseCase()
            getTopRatedMoviesUseCase()
            getPopularTvShowsUseCase()
            getTopRatedTvShowsUseCase()
            movieMapper.movieToProgram(any(), any())
            tvShowMapper.tvShowToProgram(any(), any())
        }

        Assert.assertEquals(programs, viewModel.popularMovies.value)
        Assert.assertEquals(programs, viewModel.topRatedMovies.value)
        Assert.assertEquals(programs, viewModel.popularTvShows.value)
        Assert.assertEquals(programs, viewModel.topRatedTvShows.value)
    }
}