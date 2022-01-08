package com.bd_drmwan.commonextension.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.bd_drmwan.commonextension.R

fun Fragment.toast(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.toastLong(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

fun Fragment.hideSoftKeyboard(mView: View) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(mView.windowToken, 0)
}

fun Fragment.hideStatusBar() {
    activity?.window?.let {
        WindowCompat.setDecorFitsSystemWindows(it, false)
        WindowInsetsControllerCompat(it, it.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}

fun Fragment.showStatusBar() {
    activity?.window?.let {
        WindowCompat.setDecorFitsSystemWindows(it, true)
        WindowInsetsControllerCompat(it, it.decorView).show(WindowInsetsCompat.Type.systemBars())
    }
}