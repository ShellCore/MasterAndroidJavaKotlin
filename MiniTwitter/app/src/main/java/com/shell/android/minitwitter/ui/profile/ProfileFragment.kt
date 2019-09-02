package com.shell.android.minitwitter.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.shell.android.minitwitter.BuildConfig
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.data.ProfileViewModel
import com.shell.android.minitwitter.rest.services.profile.response.UserProfileResponse
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

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

        }

        btnModifyPassword.setOnClickListener {

        }
    }

}
