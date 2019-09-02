package com.shell.android.minitwitter.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shell.android.minitwitter.extensions.getCredentialFromSharedPreferences
import com.shell.android.minitwitter.rest.base.AuthTwitterClient
import com.shell.android.minitwitter.rest.base.AuthTwitterService
import com.shell.android.minitwitter.rest.services.profile.response.UserProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository {

    private var service: AuthTwitterService
    private var client: AuthTwitterClient = AuthTwitterClient.getInstance()
    var userProfile: MutableLiveData<UserProfileResponse> = MutableLiveData()


    val username = getCredentialFromSharedPreferences()!!.userName

    init {
        service = client.authTwitterService
        userProfile = getProfile()

    }

    private fun getProfile(): MutableLiveData<UserProfileResponse> {
        service.getUserProfile()
            .enqueue(object: Callback<UserProfileResponse> {
                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        userProfile.value = response.body()
                    } else {
                        Log.e("ProfileRepository", response.message())
                    }
                }

                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    Log.e("ProfileRepository", t.localizedMessage)
                }
            })
        return userProfile
    }
}