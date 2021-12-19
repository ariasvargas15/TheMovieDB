package com.bsav.themoviedb.presentation.program

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.themoviedb.domain.Program
import com.bsav.themoviedb.domain.movie.mappers.ProgramMapper
import com.bsav.themoviedb.domain.movie.usecases.GetPopularMoviesUseCase
import com.bsav.themoviedb.domain.movie.usecases.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class ProgramsViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val programMapper: ProgramMapper
) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Program>>()
    val popularMovies: LiveData<List<Program>> get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<Program>>()
    val topRatedMovies: LiveData<List<Program>> get() = _topRatedMovies

    fun getMovies() {
        viewModelScope.launch {
            awaitAll(
                async { getPopularMovies() },
                async { getTopRatedMovies() }
            )
        }
    }

    private suspend fun getPopularMovies() {
        getPopularMoviesUseCase().map {
            it.map { movie ->
                programMapper.movieToProgram(movie)
            }
        }.collect {
            _popularMovies.value = it
        }
    }

    private suspend fun getTopRatedMovies() {
        getTopRatedMoviesUseCase().map {
            it.map { movie ->
                programMapper.movieToProgram(movie)
            }
        }.collect {
            _topRatedMovies.value = it
        }
    }
}


