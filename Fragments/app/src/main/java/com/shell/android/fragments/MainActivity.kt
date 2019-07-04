package com.shell.android.fragments

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var dayMode : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Carga del fragmento por defecto
        supportFragmentManager!!.beginTransaction()
            .add(R.id.frameContainer, DayModeFragment())
            .commit()

        fab.setOnClickListener { view ->
            var fragment : Fragment? = null

            if (dayMode) {
                fragment = NightModeFragment()
                fab.setImageResource(R.drawable.ic_day)
            } else {
                fragment = DayModeFragment()
                fab.setImageResource(R.drawable.ic_night)
            }

            dayMode = !dayMode

            supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
