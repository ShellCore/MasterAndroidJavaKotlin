package com.shell.android.minitwitter.rest.services.signup

import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse

interface SignupCallback {
    fun onSignupSuccess(response: AuthResponse)
    fun onSignupError(message : String)
}