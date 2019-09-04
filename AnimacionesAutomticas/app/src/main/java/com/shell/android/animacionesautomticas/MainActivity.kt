package com.shell.android.animacionesautomticas

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoSecondActivity(view: View) {
        val options = ActivityOptions.makeSceneTransitionAnimation(this)
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent, options.toBundle())
    }
}
