package com.shell.android.minitwitter.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shell.android.minitwitter.extensions.getCredentialFromSharedPreferences
import com.shell.android.minitwitter.extensions.saveToSharedPreferences
import com.shell.android.minitwitter.model.Credential
import com.shell.android.minitwitter.rest.base.AuthTwitterClient
import com.shell.android.minitwitter.rest.base.AuthTwitterService
import com.shell.android.minitwitter.rest.services.createprofile.request.UserProfileRequest
import com.shell.android.minitwitter.rest.services.profile.response.UserProfileResponse
import com.shell.android.minitwitter.rest.services.uploadphoto.response.UploadProfilePhotoResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProfileRepository {

    private var service: AuthTwitterService
    private var client: AuthTwitterClient = AuthTwitterClient.getInstance()
    var userProfile: MutableLiveData<UserProfileResponse> = MutableLiveData()
    var photoProfile : MutableLiveData<String> = MutableLiveData()

    val username = getCredentialFromSharedPreferences()!!.userName

    init {
        service = client.authTwitterService
        userProfile = getProfile()
    }

    private fun getProfile(): MutableLiveData<UserProfileResponse> {
        service.getUserProfile()
                .enqueue(object : Callback<UserProfileResponse> {
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

    fun updateProfile(request: UserProfileRequest) {
        service.updateUserProfile(request)
                .enqueue(object : Callback<UserProfileResponse> {
                    override fun onResponse(call: Call<UserProfileResponse>, response: Response<UserProfileResponse>) {
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
    }

    fun uploadPhoto(photoPath: String) {
        val file = File(photoPath)
        val requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file)
        service.uploadProfilePhoto(requestBody)
            .enqueue(object: Callback<UploadProfilePhotoResponse> {
                override fun onResponse(
                    call: Call<UploadProfilePhotoResponse>,
                    response: Response<UploadProfilePhotoResponse>
                ) {
                    if (response.isSuccessful) {
                        saveNewPhotoName(response.body()!!.filename)
                    } else {
                        Log.e("ProfileRepository", response.message())
                    }
                }

                override fun onFailure(call: Call<UploadProfilePhotoResponse>, t: Throwable) {
                    Log.e("ProfileRepository", t.localizedMessage)
                }
            })
    }

    private fun saveNewPhotoName(filename: String) {
        val currentUser: Credential = getCredentialFromSharedPreferences()!!
        currentUser.photoUrl = filename
        currentUser.saveToSharedPreferences()
        photoProfile.value = filename
    }


}