package com.example.pwdcripto

import android.app.Application
import com.example.pwdcripto.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PwdApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@PwdApplication)
            modules(appModule)
        }

    }


}