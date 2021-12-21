package com.bsav.themoviedb.data.tvshow

import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.repository.TvShowRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowRepositoryImplTest {

    private val localDataSource = mockk<TvShowLocalDataSource>(relaxed = true)
    private val remoteDataSource = mockk<TvShowRemoteDataSource>()
    private lateinit var repository: TvShowRepository

    @Before
    fun setUp() {
        repository = TvShowRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `given remoteDataSource response ok when getTvShowById then should emit the response`() {
        val expected = mockk<TvShow>()
        every {
            remoteDataSource.getTvShowById(1)
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getTvShowById(1).first()
        }

        coVerify(exactly = 1) {
            remoteDataSource.getTvShowById(1)
            localDataSource.saveTvShow(expected)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response fails when getTvShowById then should call localDataSource`() {
        val expected = mockk<TvShow>()
        every {
            remoteDataSource.getTvShowById(1)
        } answers {
            flow {
                throw Exception()
            }
        }
        every {
            localDataSource.getTvShowById(1)
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getTvShowById(1).first()
        }
        verify(exactly = 1) {
            remoteDataSource.getTvShowById(1)
            localDataSource.getTvShowById(1)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response ok when getPopularTvShows then should emit the response`() {
        val expected = mockk<List<TvShow>>()
        every {
            remoteDataSource.getPopularTvShows()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getPopularTvShows().first()
        }

        coVerify(exactly = 1) {
            remoteDataSource.getPopularTvShows()
            localDataSource.deleteTvShowsByType(ProgramType.TvShow.Popular)
            localDataSource.savePopularTvShows(expected)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response fails when getPopularTvShows then should call localDataSource`() {
        val expected = mockk<List<TvShow>>()
        every {
            remoteDataSource.getPopularTvShows()
        } answers {
            flow {
                throw Exception()
            }
        }
        every {
            localDataSource.getPopularTvShows()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getPopularTvShows().first()
        }
        verify(exactly = 1) {
            remoteDataSource.getPopularTvShows()
            localDataSource.getPopularTvShows()
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response ok when getTopRatedTvShows then should emit the response`() {
        val expected = mockk<List<TvShow>>()
        every {
            remoteDataSource.getTopRatedTvShows()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getTopRatedTvShows().first()
        }

        coVerify(exactly = 1) {
            remoteDataSource.getTopRatedTvShows()
            localDataSource.deleteTvShowsByType(ProgramType.TvShow.TopRated)
            localDataSource.saveTopRatedTvShows(expected)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response fails when getTopRatedTvShows then should call localDataSource`() {
        val expected = mockk<List<TvShow>>()
        every {
            remoteDataSource.getTopRatedTvShows()
        } answers {
            flow {
                throw Exception()
            }
        }
        every {
            localDataSource.getTopRatedTvShows()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getTopRatedTvShows().first()
        }
        verify(exactly = 1) {
            remoteDataSource.getTopRatedTvShows()
            localDataSource.getTopRatedTvShows()
        }

        Assert.assertEquals(expected, response)
    }
}