package com.shell.android.minitwitter.rest.services.auth.response

data class AuthResponse (

    var token : String,
    var username : String,
    var photoUrl : String,
    var email : String,
    var created : Boolean,
    var active : Boolean
)