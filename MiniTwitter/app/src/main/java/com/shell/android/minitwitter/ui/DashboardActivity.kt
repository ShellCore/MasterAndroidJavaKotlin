package com.shell.android.minitwitter.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.shell.android.minitwitter.BuildConfig
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.data.ProfileViewModel
import com.shell.android.minitwitter.extensions.getCredentialFromSharedPreferences
import com.shell.android.minitwitter.ui.profile.ProfileFragment
import com.shell.android.minitwitter.ui.tweets.NewTweetDialogFragment
import com.shell.android.minitwitter.ui.tweets.TweetsFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), PermissionListener {

    companion object {
        const val SELECT_FOTO_GALLERY = 1
    }

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this)
            .get(ProfileViewModel::class.java)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_all -> {
                    setFragmentList(
                        TweetsFragment.newInstance(
                            TweetsFragment.TWEET_LIST_ALL
                        )
                    )
                    btnFab.show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favs -> {
                    setFragmentList(
                        TweetsFragment.newInstance(
                            TweetsFragment.TWEET_LIST_FAVS
                        )
                    )
                    btnFab.hide()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    setFragmentList(ProfileFragment())
                    btnFab.hide()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun setFragmentList(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.dashboardFragmentContainer, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val navView: BottomNavigationView = findViewById(R.id.dahsboardNavView)

        supportActionBar?.hide()

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        setFragmentList(TweetsFragment.newInstance(TweetsFragment.TWEET_LIST_ALL))

        loadUserImage()
        setOnClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == SELECT_FOTO_GALLERY) {
                if (data != null) {
                    val imagenSeleccionada: Uri = data.data!!
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor =
                        contentResolver.query(
                            imagenSeleccionada,
                            filePathColumn,
                            null,
                            null,
                            null
                        )
                    if (cursor != null) {
                        cursor.moveToFirst()
                        val imageIndex = cursor.getColumnIndex(filePathColumn[0])
                        val photoPath = cursor.getString(imageIndex)
                        profileViewModel.uploadPhoto(photoPath)
                        cursor.close()
                    }
                }
            }
        }

        profileViewModel.photoProfile.observe(this, Observer {
            Glide.with(this)
                .load("${BuildConfig.API_MINITWITTER_FILES_URL}${it}")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .skipMemoryCache(true)
                .into(imgUserPhoto)
        })
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, SELECT_FOTO_GALLERY)
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "No tiene permiso para la selección de imagen", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
    }

    private fun loadUserImage() {
        with(getCredentialFromSharedPreferences()?.photoUrl) {
            Glide.with(applicationContext)
                .load("${BuildConfig.API_MINITWITTER_FILES_URL}${this}")
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .into(imgUserPhoto)
        }
    }

    private fun setOnClickListeners() {
        btnFab.setOnClickListener {
            NewTweetDialogFragment()
                .show(supportFragmentManager, "")
        }
    }
}
