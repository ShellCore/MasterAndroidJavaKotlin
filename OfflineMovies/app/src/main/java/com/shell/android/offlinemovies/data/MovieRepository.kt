package com.shell.android.offlinemovies.data

import androidx.room.Room
import com.shell.android.offlinemovies.OfflineMoviesApplication
import com.shell.android.offlinemovies.data.local.MovieRoomDatabase
import com.shell.android.offlinemovies.data.local.dao.MovieDao
import com.shell.android.offlinemovies.data.remote.MovieDBApiModule
import com.shell.android.offlinemovies.data.remote.MovieDBService

class MovieRepository {

    val movieApiService: MovieDBService by lazy {
        MovieDBApiModule.provideApiService()
    }

    val movieDao: MovieDao by lazy {
        val movieRoomDatabase = Room.databaseBuilder(
            OfflineMoviesApplication().applicationContext,
            MovieRoomDatabase::class.java,
            "db_movies"
            )
            .build()

        movieRoomDatabase.getMovieDao()
    }
}

