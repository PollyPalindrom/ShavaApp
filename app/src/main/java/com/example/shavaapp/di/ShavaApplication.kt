package com.example.shavaapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShavaApplication : Application() {
    companion object {
        lateinit var app: ShavaApplication
    }
}
