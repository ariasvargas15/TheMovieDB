package com.bsav.themoviedb.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.themoviedb.domain.Program
import com.bsav.themoviedb.domain.movie.mappers.ProgramMapper
import com.bsav.themoviedb.domain.movie.usecases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val programMapper: ProgramMapper
) : ViewModel() {

    val movies = MutableLiveData<List<Program>>()

    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase().map {
                it.map { movie ->
                    programMapper.movieToProgram(movie)
                }
            }.collect {
                movies.value = it
            }
        }
    }
}

