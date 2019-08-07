package com.shell.android.minitwitter.rest.services.tweet.response

data class User (
    var id : Int,
    var username : String,
    var description : String,
    var website : String,
    var photoUrl : String,
    var created : String
) : Comparable<User> {
    override fun compareTo(other: User): Int {
        return username.compareTo(other.username)
    }
}
