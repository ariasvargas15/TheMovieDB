package com.bsav.themoviedb.framework.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsav.themoviedb.framework.db.entities.ProgramEntity

@Dao
interface ProgramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProgramList(programList: List<ProgramEntity>)

    @Query("SELECT * FROM ProgramEntity WHERE type = :type")
    fun getProgramsByType(type: String): List<ProgramEntity>

    @Query("DELETE FROM ProgramEntity WHERE type = :type")
    fun deleteProgramsByType(type: String)
}