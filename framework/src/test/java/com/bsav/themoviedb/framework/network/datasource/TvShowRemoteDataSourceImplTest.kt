package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.tvshow.TvShowRemoteDataSource
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.framework.network.services.TvShowService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TvShowRemoteDataSourceImplTest {

    private val service = mockk<TvShowService>()
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var dataSource: TvShowRemoteDataSource

    @Before
    fun setUp() {
        dataSource = TvShowRemoteDataSourceImpl(service, dispatcher)
    }

    @Test
    fun `when getTvShowById is called then should call service`() {
        val expected = mockk<TvShow>()
        coEvery {
            service.getTvShowById(1).mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getTvShowById(1).first()
        }

        coVerify { service.getTvShowById(1) }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getPopularTvShows is called then should call service`() {
        val expected = listOf(mockk<TvShow>())
        coEvery {
            service.getPopularTvShows().mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getPopularTvShows().first()
        }

        coVerify { service.getPopularTvShows() }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getTopRatedTvShows is called then should call service`() {
        val expected = listOf(mockk<TvShow>())
        coEvery {
            service.getTopRatedTvShows().mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getTopRatedTvShows().first()
        }

        coVerify { service.getTopRatedTvShows() }
        Assert.assertEquals(expected, response)
    }
}