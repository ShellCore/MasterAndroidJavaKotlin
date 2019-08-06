package com.shell.android.minitwitter.rest.services.login

import android.content.Context
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.base.MiniTwitterClient
import com.shell.android.minitwitter.rest.base.MiniTwitterService
import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse
import com.shell.android.minitwitter.rest.services.login.request.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryImpl(private val context : Context, private val callback : LoginCallback) : LoginRepository {

    private var service: MiniTwitterService
    private var client: MiniTwitterClient = MiniTwitterClient.getInstance()

    init {
        service = client.miniTwitterService
    }

    override fun doLogin(email : String, password : String) {
        val request = LoginRequest(email, password)
        val call = service.login(request)
        call.enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    callback.onLoginSuccess(response.body()!!)
                } else {
                    callback.onLoginError(context.getString(R.string.login_message_error_noAuth))
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                callback.onLoginError(context.getString(R.string.rest_message_error_failure))
            }

        })
    }
}