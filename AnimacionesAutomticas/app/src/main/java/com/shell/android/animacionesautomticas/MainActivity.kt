package com.shell.android.animacionesautomticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cambiarVisibilidad(view: View) {
        txtInfo.apply {
            when (visibility) {
                View.VISIBLE -> visibility = View.GONE
                else -> visibility = View.VISIBLE
            }
        }
    }
}
