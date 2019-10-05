package com.shell.android.offlinemovies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shell.android.offlinemovies.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun loadPopularMovies(): LiveData<ArrayList<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<MovieEntity>)
}