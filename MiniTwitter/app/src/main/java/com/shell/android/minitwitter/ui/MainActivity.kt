package com.shell.android.minitwitter.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.base.MiniTwitterClient
import com.shell.android.minitwitter.rest.base.MiniTwitterService
import com.shell.android.minitwitter.rest.services.auth.response.AuthResponse
import com.shell.android.minitwitter.rest.services.login.request.LoginRequest
import com.shell.android.minitwitter.showMessage
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var service: MiniTwitterService
    private lateinit var client: MiniTwitterClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        initRetrofit()
        setupOnclick()
    }

    private fun initRetrofit() {
        client = MiniTwitterClient.getInstance()
        service = client.miniTwitterService

    }

    private fun setupOnclick() {
        btnLogin.setOnClickListener(this)
        btnSignup.setOnClickListener(this)
    }

    override fun onClick(itemView: View) {
        when (itemView.id) {
            R.id.btnLogin -> gotoLogin()
            R.id.btnSignup -> gotoSignup()
        }
    }

    private fun gotoLogin() {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        if (email.isEmpty()) {
            edtEmail.error = getString(R.string.login_error_email_required)
        } else if (password.isEmpty()) {
            edtPassword.error = getString(R.string.login_error_password_required)
        } else {
            val loginRequest = LoginRequest(email, password)
            val call = service.login(loginRequest)
            call.enqueue(object : Callback<AuthResponse> {

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful) {
                        mainContainer.showMessage(getString(R.string.login_message_ok))
                        gotoDashboardActivity()
                    } else {
                        mainContainer.showMessage(getString(R.string.login_message_error_noAuth))
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    mainContainer.showMessage(getString(R.string.rest_message_error_failure))
                }
            })
        }
    }

    private fun gotoSignup() {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    private fun gotoDashboardActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}
