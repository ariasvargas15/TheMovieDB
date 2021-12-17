package com.bsav.themoviedb.framework.helpers

import android.widget.ImageView
import com.bsav.themoviedb.framework.network.BASE_URL_IMAGES
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImageFromPathWithBaseUrl(path: String) {
    this.loadImageFromUrl("$BASE_URL_IMAGES$path")
}