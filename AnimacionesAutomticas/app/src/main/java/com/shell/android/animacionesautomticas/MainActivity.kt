package com.shell.android.animacionesautomticas

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var animationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationDuration = resources.getInteger(android.R.integer.config_longAnimTime)

        txtContent.visibility = View.GONE
        progressBar.setOnClickListener {
            crossfadeAnimation()
        }
    }

    private fun crossfadeAnimation() {
        txtContent.apply {
            alpha = 0f
            visibility = View.VISIBLE
        }

        txtContent.animate()
            .alpha(1f)
            .setDuration(animationDuration.toLong())

        progressBar.animate()
            .alpha(0f)
            .setDuration(animationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    progressBar.visibility = View.GONE
                }
            })
    }
}
