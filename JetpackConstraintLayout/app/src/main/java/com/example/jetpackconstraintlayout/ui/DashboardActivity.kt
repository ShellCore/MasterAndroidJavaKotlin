package com.example.jetpackconstraintlayout.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jetpackconstraintlayout.R

class DashboardActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var fragment : Fragment? = null

        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = NoteFragment()
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }

        fragment?.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, this)
                .commit()
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
            .add(
                R.id.frameContainer,
                NoteFragment()
            )
            .commit()
    }
}
