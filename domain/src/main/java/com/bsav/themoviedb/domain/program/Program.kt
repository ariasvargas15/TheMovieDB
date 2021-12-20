package com.bsav.themoviedb.domain.program

data class Program (
    val id: Int,
    val posterPath: String?,
    val type: ProgramType,
)