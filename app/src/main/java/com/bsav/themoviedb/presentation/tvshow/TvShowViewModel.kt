package com.bsav.themoviedb.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.domain.tvshow.usecases.GetTvShowById
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val getTvShowById: GetTvShowById
) : ViewModel() {

    private val _tvShow = MutableLiveData<TvShow>()
    val tvShow: LiveData<TvShow> get() = _tvShow

    fun getTvShow(id: Int) {
        viewModelScope.launch {
            getTvShowById(id).collect {
                _tvShow.value = it
            }
        }
    }
}