package com.shell.android.minitwitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupOnclick()
    }

    private fun setupOnclick() {
        btnLogin.setOnClickListener(this)
        btnSignup.setOnClickListener(this)
    }

    override fun onClick(itemView: View) {
        when (itemView.id) {
            R.id.btnLogin -> {} // TODO falta implementación onClick btnLogin
            R.id.btnSignup -> gotoSignup()
        }
    }

    private fun gotoSignup() {
        startActivity(Intent(this, SignupActivity::class.java))
    }
}
