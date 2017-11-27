package com.alexpetitjean.mvicalculator

import android.app.Application
import timber.log.Timber

class MviCalculatorApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
