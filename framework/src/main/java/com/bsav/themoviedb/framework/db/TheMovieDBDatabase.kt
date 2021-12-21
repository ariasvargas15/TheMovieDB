package com.bsav.themoviedb.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bsav.themoviedb.framework.db.daos.MovieDao
import com.bsav.themoviedb.framework.db.daos.ProgramDao
import com.bsav.themoviedb.framework.db.daos.TvShowDao
import com.bsav.themoviedb.framework.db.entities.MovieEntity
import com.bsav.themoviedb.framework.db.entities.ProgramEntity
import com.bsav.themoviedb.framework.db.entities.TvShowEntity

@Database(
    entities = [
        ProgramEntity::class,
        MovieEntity::class,
        TvShowEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class TheMovieDBDatabase : RoomDatabase() {
    abstract fun programDao(): ProgramDao
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}