package com.shell.android.minitwitter.rest.services.tweets.response

data class Tweet (
    var id : Int,
    var mensaje : String,
    var likes : List<User>,
    var user : User
)