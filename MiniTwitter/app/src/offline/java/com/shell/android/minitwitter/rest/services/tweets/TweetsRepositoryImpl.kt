package com.shell.android.minitwitter.rest.services.tweets

import android.content.Context
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.extensions.getTweetsFromJsonRawFile


class TweetsRepositoryImpl(private val context : Context, private val callback : GetAllTweetsCallback) :
    TweetsRepository {

    override fun getAllTweets() {
        val tweets = getTweetsFromJsonRawFile(context, R.raw.get_all_tweets)
        if (tweets != null) {
            callback.onGetAllTweetsSuccess(tweets)
        } else {
            callback.onGetAllTweetsError(context.getString(R.string.getAllTweets_error_fileNotFound))
        }
    }
}
