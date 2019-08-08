package com.shell.android.minitwitter.rest.services.tweets

import android.content.Context
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.base.AuthTwitterClient
import com.shell.android.minitwitter.rest.base.AuthTwitterService
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetsRepositoryImpl(private val context : Context, private val callback: GetAllTweetsCallback) : TweetsRepository {

    private var service : AuthTwitterService
    private var client : AuthTwitterClient = AuthTwitterClient.getInstance()

    init {
        service = client.authTwitterService
    }

    override fun getAllTweets() {
        val call = service.getAllTweets()
        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    callback.onGetAllTweetsSuccess(response.body()!!)
                } else {
                    callback.onGetAllTweetsError(context.getString(R.string.getAllTweets_message_error_notFound))
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                callback.onGetAllTweetsError(context.getString(R.string.rest_message_error_failure))
            }

        })
    }
}