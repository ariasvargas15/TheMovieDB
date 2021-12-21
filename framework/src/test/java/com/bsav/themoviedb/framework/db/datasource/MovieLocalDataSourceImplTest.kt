package com.bsav.themoviedb.framework.db.datasource

import com.bsav.themoviedb.data.movie.MovieLocalDataSource
import com.bsav.themoviedb.domain.movie.mapper.MovieMapper
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.framework.db.daos.MovieDao
import com.bsav.themoviedb.framework.db.daos.ProgramDao
import com.bsav.themoviedb.framework.db.entities.MovieEntity
import com.bsav.themoviedb.framework.db.entities.ProgramEntity
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
class MovieLocalDataSourceImplTest {

    private val programDao = mockk<ProgramDao>(relaxed = true)
    private val movieDao = mockk<MovieDao>(relaxed = true)
    private val movieMapper = mockk<MovieMapper>()
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var dataSource: MovieLocalDataSource

    @Before
    fun setUp() {
        dataSource = MovieLocalDataSourceImpl(programDao, movieDao, movieMapper, dispatcher)
    }

    @Test
    fun `when getMovieById is called then should call dao`() {
        val expected = mockk<Movie>()
        every {
            movieDao.getMovieById(1).mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getMovieById(1).first()
        }

        verify {
            movieDao.getMovieById(1)
        }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when saveMovie is called then should call dao`() {
        val movie = Movie(id = 1, title = "Spiderman")

        runBlocking {
            dataSource.saveMovie(movie)
        }

        verify {
            movieDao.saveMovie(MovieEntity(movie))
        }
    }

    @Test
    fun `when getPopularMovies is called then should call dao`() {
        val movie = Movie(id = 1, title = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.Movie.Popular, posterPath = null)
        val expected = listOf(movie)
        val programEntity = ProgramEntity(program)

        every {
            movieMapper.programToMovie(program)
        } answers {
            movie
        }

        every {
            programDao.getProgramsByType("MoviePopular")
        } answers {
            listOf(programEntity)
        }

        val response = runBlocking {
            dataSource.getPopularMovies().first()
        }

        verify {
            programDao.getProgramsByType("MoviePopular")
            movieMapper.programToMovie(program)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getTopRatedMovies is called then should call dao`() {
        val movie = Movie(id = 1, title = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.Movie.Popular, posterPath = null)
        val expected = listOf(movie)
        val programEntity = ProgramEntity(program)

        every {
            movieMapper.programToMovie(program)
        } answers {
            movie
        }

        every {
            programDao.getProgramsByType("MovieTopRated")
        } answers {
            listOf(programEntity)
        }

        val response = runBlocking {
            dataSource.getTopRatedMovies().first()
        }

        verify {
            programDao.getProgramsByType("MovieTopRated")
            movieMapper.programToMovie(program)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when savePopularMovies is called then should call dao`() {
        val movie = Movie(id = 1, title = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.Movie.Popular, posterPath = null)
        val programEntity = ProgramEntity(program)
        val movies = listOf(movie)
        val programs = listOf(programEntity)

        every {
            movieMapper.movieToProgram(movie, ProgramType.Movie.Popular)
        } answers {
            program
        }

        runBlocking {
            dataSource.savePopularMovies(movies)
        }

        verify {
            programDao.saveProgramList(programs)
        }
    }

    @Test
    fun `when saveTopRatedMovies is called then should call dao`() {
        val movie = Movie(id = 1, title = "Spiderman")
        val program = Program(id = 1, name = "Spiderman", type = ProgramType.Movie.TopRated, posterPath = null)
        val programEntity = ProgramEntity(program)
        val movies = listOf(movie)
        val programs = listOf(programEntity)

        every {
            movieMapper.movieToProgram(movie, ProgramType.Movie.TopRated)
        } answers {
            program
        }

        runBlocking {
            dataSource.saveTopRatedMovies(movies)
        }

        verify {
            programDao.saveProgramList(programs)
        }
    }

    @Test
    fun `when deleteMoviesByType is called then should call dao`() {
        runBlocking {
            dataSource.deleteMoviesByType(ProgramType.Movie.Popular)
        }

        verify {
            programDao.deleteProgramsByType("MoviePopular")
        }
    }

}