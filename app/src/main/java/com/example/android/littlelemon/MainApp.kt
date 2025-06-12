package com.example.android.littlelemon

import android.app.Application
import com.example.android.littlelemon.data.AppRepository

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppRepository.initialize(this)
    }
}