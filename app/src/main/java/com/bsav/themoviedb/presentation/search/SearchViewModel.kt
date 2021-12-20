package com.bsav.themoviedb.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.search.usecases.SearchProgramByName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchProgramByName: SearchProgramByName) : ViewModel() {

    private val _programs = MutableLiveData<List<Program>>()
    val programs: LiveData<List<Program>> get() = _programs

    fun searchProgram(query: String) {
        viewModelScope.launch {
            searchProgramByName(query).collectLatest {
                _programs.value = it
            }
        }
    }
}