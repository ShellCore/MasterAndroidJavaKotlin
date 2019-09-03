package com.shell.android.minitwitter.rest.services.signup

import android.content.Context
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.base.MiniTwitterClient
import com.shell.android.minitwitter.rest.base.MiniTwitterService
import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse
import com.shell.android.minitwitter.rest.services.signup.request.SignupRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupRepositoryImpl(val context: Context, val callback : SignupCallback) : SignupRepository {

    private var service: MiniTwitterService
    private var client: MiniTwitterClient = MiniTwitterClient.getInstance()

    init {
        service = client.miniTwitterService
    }

    override fun doSingup(username: String, email: String, password: String) {
        val request = SignupRequest(username, email, password)
        val call = service.signup(request)
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    callback.onSignupSuccess(response.body()!!)
                } else {
                    callback.onSignupError(context.getString(R.string.signup_message_error_noSignup))
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                callback.onSignupError(context.getString(R.string.login_message_error_noAuth))
            }
        })
    }
}