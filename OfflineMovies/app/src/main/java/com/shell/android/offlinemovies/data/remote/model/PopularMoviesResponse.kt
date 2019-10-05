package com.shell.android.offlinemovies.data.remote.model


import com.google.gson.annotations.SerializedName
import com.shell.android.offlinemovies.data.local.entity.MovieEntity

data class PopularMoviesResponse(
    val page: Int,
    val results: List<MovieEntity>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)