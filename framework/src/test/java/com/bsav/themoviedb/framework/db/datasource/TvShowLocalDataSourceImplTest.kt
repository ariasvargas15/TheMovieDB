package com.bsav.themoviedb.framework.db.datasource

import com.bsav.themoviedb.data.tvshow.TvShowLocalDataSource
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.domain.tvshow.mapper.TvShowMapper
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.framework.db.daos.ProgramDao
import com.bsav.themoviedb.framework.db.daos.TvShowDao
import com.bsav.themoviedb.framework.db.entities.ProgramEntity
import com.bsav.themoviedb.framework.db.entities.TvShowEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TvShowLocalDataSourceImplTest {

    private val programDao = mockk<ProgramDao>(relaxed = true)
    private val tvShowDao = mockk<TvShowDao>(relaxed = true)
    private val tvShowMapper = mockk<TvShowMapper>()
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var dataSource: TvShowLocalDataSource

    @Before
    fun setUp() {
        dataSource = TvShowLocalDataSourceImpl(programDao, tvShowDao, tvShowMapper, dispatcher)
    }

    @Test
    fun `when getTvShowById is called then should call dao`() {
        val expected = mockk<TvShow>()
        every {
            tvShowDao.getTvShowById(1).mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getTvShowById(1).first()
        }

        verify {
            tvShowDao.getTvShowById(1)
        }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when saveTvShow is called then should call dao`() {
        val tvShow = TvShow(id = 1, name = "Spiderman")

        runBlocking {
            dataSource.saveTvShow(tvShow)
        }

        verify {
            tvShowDao.saveTvShow(TvShowEntity(tvShow))
        }
    }

    @Test
    fun `when getPopularTvShows is called then should call dao`() {
        val tvShow = TvShow(id = 1, name = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.TvShow.Popular, posterPath = null)
        val expected = listOf(tvShow)
        val programEntity = ProgramEntity(program)

        every {
            tvShowMapper.programToTvShow(program)
        } answers {
            tvShow
        }

        every {
            programDao.getProgramsByType("TvShowPopular")
        } answers {
            listOf(programEntity)
        }

        val response = runBlocking {
            dataSource.getPopularTvShows().first()
        }

        verify {
            programDao.getProgramsByType("TvShowPopular")
            tvShowMapper.programToTvShow(program)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getTopRatedTvShows is called then should call dao`() {
        val tvShow = TvShow(id = 1, name = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.TvShow.Popular, posterPath = null)
        val expected = listOf(tvShow)
        val programEntity = ProgramEntity(program)

        every {
            tvShowMapper.programToTvShow(program)
        } answers {
            tvShow
        }

        every {
            programDao.getProgramsByType("TvShowTopRated")
        } answers {
            listOf(programEntity)
        }

        val response = runBlocking {
            dataSource.getTopRatedTvShows().first()
        }

        verify {
            programDao.getProgramsByType("TvShowTopRated")
            tvShowMapper.programToTvShow(program)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when savePopularTvShows is called then should call dao`() {
        val tvShow = TvShow(id = 1, name = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.TvShow.Popular, posterPath = null)
        val programEntity = ProgramEntity(program)
        val tvShows = listOf(tvShow)
        val programs = listOf(programEntity)

        every {
            tvShowMapper.tvShowToProgram(tvShow, ProgramType.TvShow.Popular)
        } answers {
            program
        }

        runBlocking {
            dataSource.savePopularTvShows(tvShows)
        }

        verify {
            programDao.saveProgramList(programs)
        }
    }

    @Test
    fun `when saveTopRatedTvShows is called then should call dao`() {
        val tvShow = TvShow(id = 1, name = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.TvShow.TopRated, posterPath = null)
        val programEntity = ProgramEntity(program)
        val tvShows = listOf(tvShow)
        val programs = listOf(programEntity)

        every {
            tvShowMapper.tvShowToProgram(tvShow, ProgramType.TvShow.TopRated)
        } answers {
            program
        }

        runBlocking {
            dataSource.saveTopRatedTvShows(tvShows)
        }

        verify {
            programDao.saveProgramList(programs)
        }
    }

    @Test
    fun `when deleteTvShowsByType is called then should call dao`() {
        runBlocking {
            dataSource.deleteTvShowsByType(ProgramType.TvShow.Popular)
        }

        verify {
            programDao.deleteProgramsByType("TvShowPopular")
        }
    }

}