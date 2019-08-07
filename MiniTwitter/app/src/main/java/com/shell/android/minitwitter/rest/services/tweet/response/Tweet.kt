package com.shell.android.minitwitter.rest.services.tweet.response

data class Tweet (
    var id : Int,
    var mensaje : String,
    var likes : List<User>,
    var user : User
)