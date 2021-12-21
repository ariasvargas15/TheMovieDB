package com.bsav.themoviedb.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bsav.themoviedb.framework.db.daos.ProgramDao
import com.bsav.themoviedb.framework.db.entities.ProgramEntity

@Database(entities = [ProgramEntity::class], version = 1, exportSchema = false)
abstract class TheMovieDBDatabase : RoomDatabase() {
    abstract fun programDao(): ProgramDao
}