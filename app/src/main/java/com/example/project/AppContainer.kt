package com.example.project

import android.app.Application
import com.example.project.data.AppDatabase

class AppContainer: Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getDatabase(this.applicationContext)
    }
}