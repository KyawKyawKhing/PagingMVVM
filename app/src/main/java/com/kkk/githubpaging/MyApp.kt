package com.kkk.githubpaging

import android.app.Application
import com.kkk.githubpaging.di.appModule
import org.koin.android.ext.android.startKoin

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule)
    }
}