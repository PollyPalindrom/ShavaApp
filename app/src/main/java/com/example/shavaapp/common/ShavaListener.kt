package com.example.shavaapp.common

import android.widget.ImageView

interface ShavaListener {

    fun loadImage(url: String, view: ImageView)
}