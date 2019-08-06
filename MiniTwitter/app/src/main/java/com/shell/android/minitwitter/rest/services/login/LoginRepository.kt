package com.shell.android.minitwitter.rest.services.login

interface LoginRepository {
    fun doLogin(email : String, password : String)
}