package com.bsav.themoviedb.framework.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsav.themoviedb.framework.db.entities.SerieEntity

@Dao
interface SerieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSeries(series: List<SerieEntity>)

    @Query("SELECT * FROM SerieEntity")
    fun getSeries(): List<SerieEntity>
}