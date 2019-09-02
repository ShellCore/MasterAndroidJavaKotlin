package com.shell.android.minitwitter.data

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import com.shell.android.minitwitter.ui.BottomModalTweetFragment

class TweetsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TweetsRepository = TweetsRepository()

    var tweets: LiveData<List<Tweet>>
    var favTweets: LiveData<List<Tweet>>

    init {
        tweets = repository.tweets
        favTweets = repository.favTweets
    }

    fun openDialogMenu(context: Context, idTweet: Int) {
        val dialog = BottomModalTweetFragment.newInstance(idTweet)
        dialog.show((context as AppCompatActivity).supportFragmentManager, "BottomModalTweetFragment")
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

    fun deleteTweet(idTweet: Int) {
        repository.deleteTweet(idTweet)
    }

    fun likeTweet(idTweet: Int) {
        repository.likeTweet(idTweet)
    }
}