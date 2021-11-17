package com.example.deliveryfood

import android.app.Application
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.deliveryfood.api.repository.DeliveryfoodRepository
import com.example.deliveryfood.di.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.lang.Exception
import java.security.MessageDigest

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DeliveryfoodRepository.initialize(this@MyApplication)
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(myDiModule)
            Log.d("MyApplication", "Dependency Injection")
        }

    }

}