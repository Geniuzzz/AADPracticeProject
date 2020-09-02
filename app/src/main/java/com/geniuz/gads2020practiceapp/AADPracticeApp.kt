package com.geniuz.gads2020practiceapp

import android.app.Application
import timber.log.Timber.*

class AADPracticeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        plant(DebugTree())
    }
}