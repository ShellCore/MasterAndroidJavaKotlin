package com.shell.android.minitwitter.rest.base

import com.shell.android.minitwitter.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MiniTwitterClient() {

    companion object {
        private var instance : MiniTwitterClient? = null
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_MINITWITTER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var miniTwitterService: MiniTwitterService

    init {
        miniTwitterService = retrofit.create(MiniTwitterService::class.java)
    }

    // Patrón Singleton
    fun getInstance(): MiniTwitterClient {
        if (instance == null) {
            instance = MiniTwitterClient()
        }
        return instance!!
    }


}