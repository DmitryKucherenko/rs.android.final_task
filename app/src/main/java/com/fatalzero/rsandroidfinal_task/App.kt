package com.fatalzero.rsandroidfinal_task

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.fatalzero.rsandroidfinal_task.di.AppComponent
import com.fatalzero.rsandroidfinal_task.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    var callback:Callback? = null


    override fun onCreate() {
        super.onCreate()
        callback = Callback()
        registerActivityLifecycleCallbacks(callback)
    }


    fun getCurrentActivity():Activity?=callback?.currentActivity

}

class Callback : Application.ActivityLifecycleCallbacks {
    var currentActivity:Activity?=null
    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        currentActivity=activity

    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityResumed(p0: Activity) {
       currentActivity = p0
    }

    override fun onActivityPaused(p0: Activity) {

    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }
    override fun onActivityDestroyed(p0: Activity) {
        currentActivity=null
    }
}