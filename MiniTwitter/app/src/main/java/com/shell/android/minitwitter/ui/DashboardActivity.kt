package com.shell.android.minitwitter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shell.android.minitwitter.BuildConfig
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.extensions.getCredentialFromSharedPreferences
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_all -> {
                setFragmentList(TweetsFragment.newInstance(TweetsFragment.TWEET_LIST_ALL))
                btnFab.show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favs -> {
                setFragmentList(TweetsFragment.newInstance(TweetsFragment.TWEET_LIST_FAVS))
                btnFab.hide()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
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

    private fun loadUserImage() {
        with(getCredentialFromSharedPreferences()?.photoUrl) {
            Glide.with(applicationContext)
                .load("${BuildConfig.API_MINITWITTER_FILES_URL}${this}")
                .into(imgUserPhoto)
        }
    }

    private fun setOnClickListeners() {
        btnFab.setOnClickListener {
            NewTweetDialogFragment().show(supportFragmentManager, "")
        }
    }
}
