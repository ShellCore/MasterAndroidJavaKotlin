package com.shell.android.minitwitter.rest.services.login

import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse

interface LoginCallback {
    fun onLoginSuccess(response: AuthResponse)
    fun onLoginError(message : String)
}