package com.shell.android.minitwitter.rest.services.createprofile.request

data class UserProfileRequest (
    var username : String,
    var email : String,
    var descripcion : String,
    var website : String,
    var password : String
)
