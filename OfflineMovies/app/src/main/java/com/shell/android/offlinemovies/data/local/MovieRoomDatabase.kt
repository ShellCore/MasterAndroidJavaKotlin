package com.shell.android.offlinemovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shell.android.offlinemovies.data.local.dao.MovieDao
import com.shell.android.offlinemovies.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}