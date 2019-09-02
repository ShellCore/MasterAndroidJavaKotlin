package com.shell.android.minitwitter.rest.services.profile.response

data class UserProfileResponse (
    var id : Int,
    var username : String,
    var email : String,
    var descripcion : String,
    var website : String,
    var photoUrl : String,
    var created : String
)
