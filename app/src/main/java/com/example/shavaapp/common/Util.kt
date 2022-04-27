package com.example.shavaapp.common

import android.widget.ImageView
import com.example.shavaapp.R
import com.squareup.picasso.Picasso

fun loadImgByUrl(url: String, view: ImageView) {
    if (url.isEmpty() || url.isBlank()) {
        return
    }
    Picasso.get()
        .load(url)
//            .resize(view.maxWidth, view.maxHeight)
//            .centerCrop()
        .error(R.drawable.ic_baseline_account_circle_24)
        .into(view)
}