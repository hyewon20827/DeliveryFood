package com.example.deliveryfood

import android.app.Application
import android.util.Log
import com.example.deliveryfood.di.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(myDiModule)
            Log.d("MyApplication", "Dependency Injection")
        }
    }
}