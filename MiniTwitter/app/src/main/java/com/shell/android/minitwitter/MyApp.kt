package com.shell.android.minitwitter

import android.app.Application

class MyApp : Application() {

    companion object {
        var instance : MyApp? = null
        fun getContext() = instance
    }

    fun getInstance() = instance

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}