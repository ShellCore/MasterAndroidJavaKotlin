package com.shell.android.minitwitter.rest.services.signup

import android.content.Context
import com.shell.android.minitwitter.R

class SignupRepositoryImpl(private val context: Context, private val listener : SignupCallback) : SignupRepository {

    override fun doSingup(username: String, email: String, password: String) {
        when {
            username != "Cesar" -> listener.onFailure(context.getString(R.string.rest_message_error_failure))
            email != "abc@abc.com" -> listener.onFailure(context.getString(R.string.rest_message_error_failure))
            password != "123456" -> listener.onFailure(context.getString(R.string.rest_message_error_failure))
            else -> listener.onSuccess()
        }
    }

    interface SignupCallback {
        fun onSuccess()
        fun onFailure(message : String)
    }
}