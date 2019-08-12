package com.shell.android.minitwitter.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shell.android.minitwitter.rest.base.AuthTwitterClient
import com.shell.android.minitwitter.rest.base.AuthTwitterService
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetsRepository() {

    private var service : AuthTwitterService
    private var client : AuthTwitterClient = AuthTwitterClient.getInstance()
    var tweets : LiveData<List<Tweet>>

    init {
        service = client.authTwitterService
        tweets = getAllTweets()
    }

    private fun getAllTweets() : LiveData<List<Tweet>> {
        val data : MutableLiveData<List<Tweet>> = MutableLiveData()

        val call = service.getAllTweets()
        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    data.value = response.body()!!
                } else {
                    Log.e("TweetsRepository", response.message())
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Log.e("TweetsRepository", t.localizedMessage)
            }

        })

        return data
    }
}