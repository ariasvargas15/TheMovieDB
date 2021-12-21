package com.bsav.themoviedb.framework.di

import android.content.Context
import androidx.room.Room
import com.bsav.themoviedb.framework.db.TheMovieDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): TheMovieDBDatabase {
        return Room.databaseBuilder(context, TheMovieDBDatabase::class.java, DATABASE_NAME).build()
    }

}

private const val DATABASE_NAME = "themoviedb-database"