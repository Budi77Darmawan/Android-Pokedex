package com.bd_drmwan.commonextension.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import com.bd_drmwan.commonextension.R

fun Activity.toast(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Activity.toastLong(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Activity.hideSoftKeyboard(mView: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(mView.windowToken, 0)
}

fun Activity.hideStatusBar() {
    window.let {
        WindowCompat.setDecorFitsSystemWindows(it, false)
        WindowInsetsControllerCompat(it, it.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}

fun Activity.showStatusBar() {
    window.let {
        WindowCompat.setDecorFitsSystemWindows(it, true)
        WindowInsetsControllerCompat(it, it.decorView).show(WindowInsetsCompat.Type.systemBars())
    }
}
