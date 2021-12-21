package com.bsav.themoviedb.framework.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsav.themoviedb.framework.db.entities.TvShowEntity

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM TvShowEntity WHERE id = :id")
    fun getTvShowById(id: Int): TvShowEntity

}