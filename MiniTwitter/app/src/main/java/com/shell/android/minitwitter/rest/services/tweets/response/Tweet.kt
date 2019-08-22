package com.shell.android.minitwitter.rest.services.tweets.response

data class Tweet (
    var id : Int,
    var mensaje : String,
    var likes : List<User>,
    var user : User
) {

    constructor(tweet: Tweet) : this(
        tweet.id,
        tweet.mensaje,
        tweet.likes,
        tweet.user
    )
}