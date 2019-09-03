package com.shell.android.minitwitter.rest.services.signup

interface SignupRepository {

    fun doSingup(username: String, email: String, password: String)
}