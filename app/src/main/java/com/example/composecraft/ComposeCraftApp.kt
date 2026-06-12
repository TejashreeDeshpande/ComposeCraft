package com.example.composecraft

import android.app.Application
import com.example.composecraft.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComposeCraftApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ComposeCraftApp)
            modules(appModule)
        }
    }
}
