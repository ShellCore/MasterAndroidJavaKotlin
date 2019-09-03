package com.shell.android.minitwitter.extensions

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.shell.android.minitwitter.MyApp
import com.shell.android.minitwitter.model.Credential

const val APP_SETTINGS_FILE = "APP_SETTINGS"
const val CREDENTIAL = "PREF_CREDENTIAL"

fun Credential.saveToSharedPreferences() {
    val json = Gson().toJson(this)
    getSharedPreferences().edit().apply {
        putString(CREDENTIAL, json)
        apply()
    }
}

fun getCredentialFromSharedPreferences() : Credential? {
    val json = getSharedPreferences().getString(CREDENTIAL, null)
    return if (json != null) {
        Gson().fromJson(json, Credential::class.java)
    } else {
        null
    }
}

private fun getSharedPreferences(): SharedPreferences =
    MyApp.getContext()!!.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
