package com.shell.android.minitwitter.rest.base

import com.shell.android.minitwitter.rest.services.createtweet.request.NewTweetRequest
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthTwitterService {

    @GET("tweets/all")
    fun getAllTweets(): Call<List<Tweet>>

    @POST("tweets/create")
    fun postNewTweet(@Body request: NewTweetRequest): Call<Tweet>

    @POST("tweets/like/{idTweet}")
    fun likeTweet(@Path("idTweet") idTweet: Int): Call<Tweet>
}