package com.fatalzero.rsandroidfinal_task

import android.app.Application
import com.fatalzero.rsandroidfinal_task.di.AppComponent
import com.fatalzero.rsandroidfinal_task.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}

