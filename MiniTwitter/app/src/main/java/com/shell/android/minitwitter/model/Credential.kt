package com.shell.android.minitwitter.model

data class Credential (

    var token : String,
    var userName : String,
    var email : String,
    var photoUrl : String,
    var created : Boolean,
    var emailActive : Boolean
)