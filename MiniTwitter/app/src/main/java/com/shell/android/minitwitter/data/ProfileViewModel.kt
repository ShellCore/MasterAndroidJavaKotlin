package com.shell.android.minitwitter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shell.android.minitwitter.rest.services.createprofile.request.UserProfileRequest
import com.shell.android.minitwitter.rest.services.profile.response.UserProfileResponse

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    val profileRepository : ProfileRepository = ProfileRepository()
    var userProfile : LiveData<UserProfileResponse>
    var photoProfile : LiveData<String>

    init {
        userProfile = profileRepository.userProfile
        photoProfile = profileRepository.photoProfile
    }

    fun updateProfile(request: UserProfileRequest) {
        profileRepository.updateProfile(request)
    }

    fun uploadPhoto(photoPath: String) {
        profileRepository.uploadPhoto(photoPath)
    }
}
