package com.shell.android.minitwitter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.services.signup.SignupRepository
import com.shell.android.minitwitter.rest.services.signup.SignupRepositoryImpl
import com.shell.android.minitwitter.extensions.showMessage
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class SignupActivity : AppCompatActivity(), View.OnClickListener, SignupRepositoryImpl.SignupCallback {

    companion object {
        const val MIN_LENGTH = 4
    }
    private lateinit var repository: SignupRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar!!.hide()

        initRepository()
        setupOnclick()
    }

    override fun onClick(itemView: View) {
        when(itemView.id) {
            R.id.btnSignup -> gotoSignup()
            R.id.btnLogin -> gotoLogin()
        }
    }

    override fun onSuccess() {
        gotoDashboardActivity()
    }

    override fun onFailure(message: String) {
        signupContainer.showMessage(message)
    }

    private fun initRepository() {
        repository = SignupRepositoryImpl(this, this)
    }

    private fun setupOnclick() {
        btnSignup.setOnClickListener(this)
        btnLogin.setOnClickListener(this)

    }

    private fun gotoSignup() {
        val username = edtUsername.text.toString()
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        when {
            username.isEmpty() -> edtUsername.error = getString(R.string.edt_error_required)
            email.isEmpty() -> edtEmail.error = getString(R.string.edt_error_required)
            password.isEmpty() -> edtPassword.error = getString(R.string.edt_error_required)
            password.length < MIN_LENGTH -> edtPassword.error = String.format(Locale.getDefault(), getString(R.string.edt_error_min_length), MIN_LENGTH)
            else -> repository.doSingup(username,  email, password)
        }
    }

    private fun gotoLogin() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun gotoDashboardActivity() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}
