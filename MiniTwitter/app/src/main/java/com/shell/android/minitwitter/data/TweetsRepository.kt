package com.shell.android.minitwitter.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shell.android.minitwitter.rest.base.AuthTwitterClient
import com.shell.android.minitwitter.rest.base.AuthTwitterService
import com.shell.android.minitwitter.rest.services.createtweet.request.NewTweetRequest
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetsRepository {

    private var service: AuthTwitterService
    private var client: AuthTwitterClient = AuthTwitterClient.getInstance()
    var tweets: MutableLiveData<List<Tweet>> = MutableLiveData()

    init {
        service = client.authTwitterService
        tweets = getAllTweets()
    }

    fun getAllTweets(): MutableLiveData<List<Tweet>> {
        service.getAllTweets()
            .enqueue(object : Callback<List<Tweet>> {
                override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                    if (response.isSuccessful) {
                        tweets.value = response.body()!!
                    } else {
                        Log.e("TweetsRepository", response.message())
                    }
                }

                override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                    Log.e("TweetsRepository", t.localizedMessage)
                }

            })

        return tweets
    }

    fun createTweet(mensaje: String) {
        val request = NewTweetRequest(mensaje)
        val call = service.postNewTweet(request)
        call.enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    var listaClonada: ArrayList<Tweet> = ArrayList()
                    listaClonada.add(response.body()!!)
                    tweets.value!!.iterator().forEach {
                        listaClonada.add(Tweet(it))
                    }
                    tweets.value = listaClonada
                } else {
                    Log.e("TweetsRepository", response.message())
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                Log.e("TweetsRepository", t.localizedMessage)
            }

        })
    }

    fun likeTweet(idTweet: Int) {
        service.likeTweet(idTweet)
            .enqueue(object: Callback<Tweet> {
                override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                    if (response.isSuccessful) {
                        var listaClonada: ArrayList<Tweet> = ArrayList()
                        tweets.value!!.iterator().forEach {
                            if (it.id == idTweet) {
                                listaClonada.add(response.body()!!)
                            } else {
                                listaClonada.add(it)
                            }
                        }
                        tweets.value = listaClonada
                    } else {
                        Log.e("TweetsRepository", response.message())
                    }
                }

                override fun onFailure(call: Call<Tweet>, t: Throwable) {
                    Log.e("TweetsRepository", t.localizedMessage)
                }

            })

    }
}