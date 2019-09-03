package com.shell.android.minitwitter.ui.tweets

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.shell.android.minitwitter.BuildConfig
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.data.TweetsViewModel
import com.shell.android.minitwitter.extensions.getCredentialFromSharedPreferences
import kotlinx.android.synthetic.main.fragment_new_tweet_dialog.*

class NewTweetDialogFragment : DialogFragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_new_tweet_dialog, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserPhoto()
        setOnClickListeners()
    }

    private fun loadUserPhoto() {
        with(getCredentialFromSharedPreferences()?.photoUrl) {
            Glide.with(activity!!)
                .load("${BuildConfig.API_MINITWITTER_FILES_URL}${this}")
                .into(imgPhoto)
        }
    }

    private fun setOnClickListeners() {
        btnTweet.setOnClickListener(this)
        btnClose.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnTweet -> {
                saveNewTweet()
            }
            R.id.btnClose -> {
                closeDialog()
            }
        }
    }

    private fun closeDialog() {
        val mensaje = edtMessage.text
        if (mensaje.isNotEmpty()) {
            showDialogConfirmation()
        } else {
            dialog.dismiss()
        }
    }

    private fun saveNewTweet() {
        val message = edtMessage.text

        if (message.isNotEmpty()) {
            val viewModel = ViewModelProviders.of(activity!!)
                .get(TweetsViewModel::class.java)
            viewModel.addNewTweet(message.toString())
            closeNewTweetDialog()
        } else {
            Toast.makeText(context, R.string.newTweet_message_error_noMessage, Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun showDialogConfirmation() {
        AlertDialog.Builder(activity).apply {
            setTitle(getString(R.string.newTweet_cancel_title))
            setMessage(R.string.newTweet_cancel_message)
            setPositiveButton(R.string.newTweet_cancel_btn_accept) { d, _ ->
                closeNewTweetDialog()
                d.dismiss()
            }
            setNegativeButton(R.string.newTweet_cancel_btn_cancel) { d, _ ->
                d.dismiss()
            }
            create()
            show()
        }
    }

    private fun closeNewTweetDialog() {
        dismiss()
    }
}
