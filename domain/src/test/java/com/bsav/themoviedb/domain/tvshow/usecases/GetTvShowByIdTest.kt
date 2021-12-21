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

class GetTvShowByIdTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private lateinit var getTvShowById: GetTvShowById

    @Before
    fun setUp() {
        getTvShowById = GetTvShowById(tvShowRepository)
    }

    @Test
    fun `when invoke is called then should call repository and return its answer`() {
        val expected = mockk<TvShow>()

        every {
            tvShowRepository.getTvShowById(1)
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            getTvShowById(1).first()
        }

        verify(exactly = 1) {
            tvShowRepository.getTvShowById(1)
        }
        Assert.assertEquals(expected, response)
    }
}