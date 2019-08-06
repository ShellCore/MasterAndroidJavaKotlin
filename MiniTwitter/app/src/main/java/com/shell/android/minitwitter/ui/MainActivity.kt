package com.shell.android.minitwitter.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.extensions.saveToSharedPreferences
import com.shell.android.minitwitter.model.Credential
import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse
import com.shell.android.minitwitter.rest.services.login.LoginCallback
import com.shell.android.minitwitter.rest.services.login.LoginRepository
import com.shell.android.minitwitter.rest.services.login.LoginRepositoryImpl
import com.shell.android.minitwitter.extensions.showMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, LoginCallback {




    private lateinit var repository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        initRepository()
        setupOnclick()
    }

    override fun onLoginSuccess(response: AuthResponse) {
        saveCredentials(response)
        gotoDashboardActivity()
    }

    override fun onLoginError(message: String) {
        mainContainer.showMessage(message)
    }

    override fun onClick(itemView: View) {
        when (itemView.id) {
            R.id.btnLogin -> gotoLogin()
            R.id.btnSignup -> gotoSignup()
        }
    }

    private fun initRepository() {
        repository = LoginRepositoryImpl(this, this)
    }

    private fun setupOnclick() {
        btnLogin.setOnClickListener(this)
        btnSignup.setOnClickListener(this)
    }

    private fun gotoLogin() {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        when {
            email.isEmpty() -> edtEmail.error = getString(R.string.edt_error_required)
            password.isEmpty() -> edtPassword.error = getString(R.string.edt_error_required)
            else -> repository.doLogin(email, password)
        }
    }

    private fun gotoSignup() = startActivity(Intent(this, SignupActivity::class.java))

    private fun saveCredentials(response : AuthResponse) {
        val credential = Credential(
            response.token,
            response.username,
            response.email,
            response.photoUrl,
            response.created,
            response.active)

        credential.saveToSharedPreferences()
    }

    private fun gotoDashboardActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}

