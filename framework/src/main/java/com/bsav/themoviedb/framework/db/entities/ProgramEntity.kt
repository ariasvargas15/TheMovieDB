package com.bsav.themoviedb.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.mapStringToProgramType

@Entity
data class ProgramEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val posterPath: String?,
    val type: String,
) {
    constructor(program: Program) : this(
        program.id,
        program.name,
        program.posterPath,
        program.type.mapToString()
    )

    fun mapToDomain() = Program(
        id,
        name,
        posterPath,
        type.mapStringToProgramType()
    )
}