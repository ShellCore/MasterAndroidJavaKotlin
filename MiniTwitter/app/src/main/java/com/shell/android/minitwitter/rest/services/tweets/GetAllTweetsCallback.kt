package com.shell.android.minitwitter.rest.services.tweets

import com.shell.android.minitwitter.rest.services.tweets.response.Tweet

interface GetAllTweetsCallback {
    fun onGetAllTweetsSuccess(tweets: List<Tweet>)
    fun onGetAllTweetsError(message : String)
}
