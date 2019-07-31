package com.shell.android.minitwitter.rest.services.signup.request

import com.shell.android.minitwitter.BuildConfig

data class SignupRequest (
    var username : String,
    var email : String,
    var password : String,
    var code : String = BuildConfig.KEY_CODE
)