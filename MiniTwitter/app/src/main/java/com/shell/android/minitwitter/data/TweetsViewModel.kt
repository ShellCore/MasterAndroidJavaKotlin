package com.shell.android.minitwitter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet

class TweetsViewModel( application : Application) : AndroidViewModel(application) {

    private val repository: TweetsRepository = TweetsRepository()
    var tweets : LiveData<List<Tweet>>

    init {
        tweets = repository.tweets
    }
}