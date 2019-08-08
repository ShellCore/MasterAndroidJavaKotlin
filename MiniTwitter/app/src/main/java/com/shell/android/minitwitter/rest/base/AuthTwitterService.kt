package com.shell.android.minitwitter.rest.base

import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import retrofit2.Call
import retrofit2.http.GET

interface AuthTwitterService {

    @GET("tweets/all")
    fun getAllTweets() : Call<List<Tweet>>
}