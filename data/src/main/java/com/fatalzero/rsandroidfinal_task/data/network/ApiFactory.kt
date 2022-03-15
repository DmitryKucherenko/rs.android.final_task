package com.fatalzero.rsandroidfinal_task.data.network


import android.content.Context
import com.fatalzero.rsandroidfinal_task.AppComponentProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject


class ApiFactory @Inject constructor(private val context: Context) {

    init {
        (context.applicationContext as AppComponentProvider).inject(this)
    }

    private val BASE_URL = "https://v2.jokeapi.dev"

    @Inject
    lateinit var okhttp: OkHttpClient

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var JOKE_SERVICE: ApiService

}




