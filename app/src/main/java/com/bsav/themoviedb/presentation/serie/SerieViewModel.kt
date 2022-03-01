package com.bsav.themoviedb.presentation.serie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.themoviedb.domain.serie.model.Serie
import com.bsav.themoviedb.domain.serie.usecase.GetSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SerieViewModel @Inject constructor(
    private val getSeries: GetSeries
) : ViewModel() {

    private val _series = MutableLiveData<SerieState>()
    val series: LiveData<SerieState> get() = _series

    fun getPopularSeries() {
        viewModelScope.launch {
            val series = getSeries()
            _series.value = SerieState.Series(series)
        }
    }
}

sealed class SerieState {
    object Loading : SerieState()
    data class Series(val list: List<Serie>) : SerieState()
    object Error : SerieState()
}