package com.bd_drmwan.commonextension.extensions

import android.view.View

fun View.visible() { this.visibility = View.VISIBLE }
fun View.invisible() { this.visibility = View.INVISIBLE }
fun View.gone() { this.visibility = View.GONE }