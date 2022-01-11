package com.bd_drmwan.commonextension.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bd_drmwan.commonextension.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(
    uri: String,
    progressColor: Int = R.color.progressColor
) {
    val colorCompat = ContextCompat.getColor(this.context, progressColor)
    Glide.with(this)
        .load(uri)
        .placeholder(getProgressDrawable(this.context, colorCompat))
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

fun getProgressDrawable(context: Context, color: Int): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 30f
        setColorSchemeColors(color)
        start()
    }
}