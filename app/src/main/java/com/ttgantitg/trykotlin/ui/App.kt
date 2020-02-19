package com.ttgantitg.trykotlin.ui

import android.app.Application
import com.ttgantitg.trykotlin.data.di.appModule
import com.ttgantitg.trykotlin.data.di.mainModule
import com.ttgantitg.trykotlin.data.di.noteModule
import com.ttgantitg.trykotlin.data.di.splashModule
import org.koin.android.ext.android.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}