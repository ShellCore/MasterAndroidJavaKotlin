package com.shell.android.minitwitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setupOnclick()
    }

    private fun setupOnclick() {
        btnSignup.setOnClickListener(this)
        btnLogin.setOnClickListener(this)

    }

    override fun onClick(itemView: View) {
        when(itemView.id) {
            R.id.btnSignup -> {} // TODO Falta implementación onClick btnSignUp
            R.id.btnLogin -> gotoLogin()
        }
    }

    private fun gotoLogin() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
