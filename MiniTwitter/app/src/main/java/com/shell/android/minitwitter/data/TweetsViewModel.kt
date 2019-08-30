package com.shell.android.minitwitter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet

class TweetsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TweetsRepository = TweetsRepository()

    var tweets: LiveData<List<Tweet>>
    var favTweets: LiveData<List<Tweet>>

    init {
        tweets = repository.tweets
        favTweets = repository.favTweets
    }

    fun getNewTweets(): LiveData<List<Tweet>> {
        tweets = repository.getAllTweets()
        return tweets
    }

    fun getNewFavTweets(): LiveData<List<Tweet>> {
        getNewTweets()
        return favTweets
    }

    fun addNewTweet(message: String) {
        repository.createTweet(message)
    }

    fun likeTweet(idTweet: Int) {
        repository.likeTweet(idTweet)
    }
}