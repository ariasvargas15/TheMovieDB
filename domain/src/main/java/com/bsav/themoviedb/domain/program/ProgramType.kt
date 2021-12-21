package com.bsav.themoviedb.domain.program

sealed class ProgramType {
    object Movie : ProgramType()
    object TvShow : ProgramType()
    object Other : ProgramType()
}