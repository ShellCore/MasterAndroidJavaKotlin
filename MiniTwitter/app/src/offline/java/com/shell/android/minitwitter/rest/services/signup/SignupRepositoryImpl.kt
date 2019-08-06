package com.shell.android.minitwitter.rest.services.signup

import android.content.Context
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse

class SignupRepositoryImpl(private val context: Context, private val callback : SignupCallback) : SignupRepository {

    override fun doSingup(username: String, email: String, password: String) {
        when {
            username != "Cesar" -> callback.onSignupError(context.getString(R.string.rest_message_error_failure))
            email != "abc@abc.com" -> callback.onSignupError(context.getString(R.string.rest_message_error_failure))
            password != "123456" -> callback.onSignupError(context.getString(R.string.rest_message_error_failure))
            else -> callback.onSignupSuccess(getDummyResponse())
        }
    }

    private fun getDummyResponse() =
        AuthResponse("123456789", "shell_core", "", "abc@def.com", created = true, active = true)
}