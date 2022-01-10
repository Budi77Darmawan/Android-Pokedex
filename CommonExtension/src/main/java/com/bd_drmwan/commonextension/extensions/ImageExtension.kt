package com.bd_drmwan.commonextension.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bd_drmwan.commonextension.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri: String) {
    Glide.with(this)
        .load(uri)
        .placeholder(getProgressDrawable(this.context))
        .into(this)
}

fun ImageView.loadImage(drawable: Drawable?) {
    Glide.with(this)
        .load(drawable)
        .into(this)
}

fun ImageView.loadImage(drawable: Int?) {
    Glide.with(this)
        .load(drawable)
        .into(this)
}

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 30f
        setColorSchemeColors(ContextCompat.getColor(context, R.color.progressColor))
        start()
    }
}