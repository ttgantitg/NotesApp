package com.ttgantitg.trykotlin.ui

import android.app.Application
import com.ttgantitg.trykotlin.di.appModule
import com.ttgantitg.trykotlin.di.mainModule
import com.ttgantitg.trykotlin.di.noteModule
import com.ttgantitg.trykotlin.di.splashModule
import org.koin.android.ext.android.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}