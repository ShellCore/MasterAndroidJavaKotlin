package com.shell.android.minitwitter.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.shell.android.minitwitter.BuildConfig
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.data.ProfileViewModel
import com.shell.android.minitwitter.extensions.getText
import com.shell.android.minitwitter.rest.services.createprofile.request.UserProfileRequest
import com.shell.android.minitwitter.rest.services.profile.response.UserProfileResponse
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private var loadingData = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickListeners()
        viewModel.userProfile.observe(activity!!, Observer {
            setUserData(it)
            if (!loadingData) {
                btnSave.isEnabled = true
                Toast.makeText(activity!!, "Datos guardados correctamente", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun setUserData(userProfile: UserProfileResponse) {
        tilUsername.editText!!.setText(userProfile.username)
        tilEmail.editText!!.setText(userProfile.email)
        tilWebsite.editText!!.setText(userProfile.website)
        tilDescription.editText!!.setText(userProfile.descripcion)
        setUserImage(userProfile)
    }

    private fun setUserImage(userProfile: UserProfileResponse) {
        with(userProfile.photoUrl) {
            Glide.with(activity!!)
                .load("${BuildConfig.API_MINITWITTER_FILES_URL}${this}")
                .into(imgAvatar)
        }
    }

    private fun setOnClickListeners() {
        btnSave.setOnClickListener {
            val username = tilUsername.getText()
            val email = tilEmail.getText()
            val description = tilDescription.getText()
            val website = tilWebsite.getText()
            val password = tilPassword.getText()

            when {
                username.isEmpty() -> tilUsername.error = getString(R.string.profile_message_error_userRequired)
                email.isEmpty() -> tilEmail.error = getString(R.string.profile_message_error_emailRequired)
                password.isEmpty() -> tilPassword.error = getString(R.string.profile_message_error_passwordRequired)
                else -> {
                    tilUsername.error = null
                    tilEmail.error = null
                    tilPassword.error = null
                    val request = UserProfileRequest(username, email, description, website, password)
                    viewModel.updateProfile(request)
                    Toast.makeText(activity!!, getString(R.string.updateProfile_message_sendingData), Toast.LENGTH_SHORT)
                        .show()
                    btnSave.isEnabled = false
                    loadingData = false
                }
            }
        }

        btnModifyPassword.setOnClickListener {

        }
    }

}
