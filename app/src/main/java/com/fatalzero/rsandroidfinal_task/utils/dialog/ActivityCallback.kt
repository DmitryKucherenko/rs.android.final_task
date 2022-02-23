package com.fatalzero.rsandroidfinal_task.utils.dialog

import android.app.Activity
import android.app.Application
import android.os.Bundle


class ActivityCallback : Application.ActivityLifecycleCallbacks {
    var currentActivity: Activity? = null


    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        currentActivity = activity
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
    }
}