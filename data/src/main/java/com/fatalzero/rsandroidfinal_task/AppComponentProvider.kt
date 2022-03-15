package com.fatalzero.rsandroidfinal_task

import android.content.Context
import com.fatalzero.rsandroidfinal_task.data.network.ApiFactory

interface AppComponentProvider {
    fun inject(apiFactory: ApiFactory)
}