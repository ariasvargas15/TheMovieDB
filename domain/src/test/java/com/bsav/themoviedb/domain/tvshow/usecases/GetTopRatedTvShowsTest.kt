package com.bsav.themoviedb.domain.tvshow.usecases

import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.repository.TvShowRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTopRatedTvShowsTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private lateinit var getTopRatedTvShows: GetTopRatedTvShows

    @Before
    fun setUp() {
        getTopRatedTvShows = GetTopRatedTvShows(tvShowRepository)
    }

    @Test
    fun `when invoke is called then should call repository and return its answer`() {
        val expected = listOf(mockk<TvShow>())

        every {
            tvShowRepository.getTopRatedTvShows()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            getTopRatedTvShows().first()
        }

        verify(exactly = 1) {
            tvShowRepository.getTopRatedTvShows()
        }
        Assert.assertEquals(expected, response)
    }
}