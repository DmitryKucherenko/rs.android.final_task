package com.fatalzero.rsandroidfinal_task

import android.app.Application
import com.fatalzero.rsandroidfinal_task.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(dataModule, domainModule, presentationModule, viewModelModule,utilsModule))
        }
    }

}
