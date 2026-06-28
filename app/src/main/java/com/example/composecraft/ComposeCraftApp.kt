package com.example.composecraft

import android.app.Application
import com.example.composecraft.di.appModule
import com.example.composecraft.features.instacart.di.instacartModule
import com.example.composecraft.features.pulseinvest.di.pulseModules
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
            modules(pulseModules)
            modules(instacartModule)
        }
    }
}
