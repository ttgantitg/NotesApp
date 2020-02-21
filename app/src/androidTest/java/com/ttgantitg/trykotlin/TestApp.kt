package com.ttgantitg.trykotlin

import android.app.Application
import org.koin.android.ext.android.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, emptyList())
    }
}