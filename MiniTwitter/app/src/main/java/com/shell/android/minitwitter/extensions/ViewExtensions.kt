package com.shell.android.minitwitter.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showMessage(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(this, message, duration).show()
