package com.shell.android.minitwitter.rest.base

import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse
import com.shell.android.minitwitter.rest.services.login.request.LoginRequest
import com.shell.android.minitwitter.rest.services.signup.request.SignupRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MiniTwitterService {

    @POST("/auth/login")
    fun login(@Body request: LoginRequest) : Call<AuthResponse>

    @POST("/auth/signup")
    fun signup(@Body request: SignupRequest) : Call<AuthResponse>
}
