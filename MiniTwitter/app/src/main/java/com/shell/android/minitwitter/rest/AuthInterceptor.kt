package com.shell.android.minitwitter.rest

import com.shell.android.minitwitter.extensions.getCredentialFromSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getCredentialFromSharedPreferences()?.token
        val request : Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}