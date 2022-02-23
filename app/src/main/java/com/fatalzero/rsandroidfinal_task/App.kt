package com.fatalzero.rsandroidfinal_task

import android.app.Activity
import android.app.Application
import com.fatalzero.rsandroidfinal_task.di.AppComponent
import com.fatalzero.rsandroidfinal_task.di.DaggerAppComponent
import com.fatalzero.rsandroidfinal_task.utils.dialog.ActivityCallback
import com.fatalzero.rsandroidfinal_task.utils.dialog.CurrentActivity

class App : Application(),CurrentActivity {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    var activityActivityCallback: ActivityCallback? = null

    override fun onCreate() {
        super.onCreate()
        activityActivityCallback = ActivityCallback()
        registerActivityLifecycleCallbacks(activityActivityCallback)
    }

    override fun getCurrentActivity():Activity?=activityActivityCallback?.currentActivity

}

