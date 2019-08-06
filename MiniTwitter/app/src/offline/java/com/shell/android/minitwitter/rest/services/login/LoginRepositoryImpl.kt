package com.shell.android.minitwitter.rest.services.login

import android.content.Context
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse

class LoginRepositoryImpl(private val context : Context, private val callback: LoginCallback) : LoginRepository {

    override fun doLogin(email : String, password : String) {
        when {
            email != "abc@def.com" -> callback.onLoginError(context.getString(R.string.rest_message_error_failure))
            password != "123456" -> callback.onLoginError(context.getString(R.string.rest_message_error_failure))
            else -> {
                callback.onLoginSuccess(getDummyResponse())
            }

        }
    }

    private fun getDummyResponse() =
        AuthResponse("123456789", "shell_core", "", "abc@def.com", created = true, active = true)
}