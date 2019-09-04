package com.shell.android.animacionesautomticas

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var animationDrawable : AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgCheck.setBackgroundResource(R.drawable.animation_check)
        animationDrawable = imgCheck.background as AnimationDrawable

        imgCheck.setOnClickListener {
            animationDrawable!!.start()
        }
    }
}
