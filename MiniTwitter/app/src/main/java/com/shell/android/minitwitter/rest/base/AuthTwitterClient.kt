package com.shell.android.minitwitter.rest.base

import com.shell.android.minitwitter.BuildConfig
import com.shell.android.minitwitter.rest.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthTwitterClient {

    companion object {
        private var instance : AuthTwitterClient? = null

        // Patrón Singleton
        fun getInstance(): AuthTwitterClient {
            if (instance == null) {
                instance = AuthTwitterClient()
            }
            return instance!!
        }
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_MINITWITTER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getAuthorizedClient())
        .build()

    var authTwitterService: AuthTwitterService

    init {
        authTwitterService = retrofit.create(AuthTwitterService::class.java)
    }

    private fun getAuthorizedClient() =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
}