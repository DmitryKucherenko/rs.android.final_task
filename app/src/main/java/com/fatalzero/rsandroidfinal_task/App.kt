package com.fatalzero.rsandroidfinal_task

import android.app.Application
import com.fatalzero.rsandroidfinal_task.data.network.ApiFactory
import com.fatalzero.rsandroidfinal_task.di.AppComponent
import com.fatalzero.rsandroidfinal_task.di.DaggerAppComponent

class App : Application(),AppComponentProvider {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun inject(apiFactory: ApiFactory) {
        appComponent.inject(apiFactory)
    }
}
