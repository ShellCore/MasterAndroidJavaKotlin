package com.shell.android.offlinemovies.data.remote

import com.shell.android.offlinemovies.data.remote.model.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieDBService {

    @GET("movie/popular")
    fun getPopularMovies() : Call<PopularMoviesResponse>
}