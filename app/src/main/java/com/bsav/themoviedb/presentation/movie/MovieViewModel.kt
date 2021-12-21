package com.bsav.themoviedb.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.usecases.GetMovieById
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun getMovie(id: Int) {
        viewModelScope.launch {
            getMovieById(id).collect {
                _movie.value = it
            }
        }
    }
}