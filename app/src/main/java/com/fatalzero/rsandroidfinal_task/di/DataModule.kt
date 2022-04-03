@file:JvmName("DataModuleKt")

package com.fatalzero.rsandroidfinal_task.di

import android.content.Context
import com.fatalzero.rsandroidfinal_task.data.network.ApiService
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.data.repository.SettingsRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import com.fatalzero.rsandroidfinal_task.domain.usecase.IJokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.SendJoke
import com.fatalzero.rsandroidfinal_task.domain.utils.SendJokeImpl
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import org.koin.dsl.module


private val BASE_URL = "https://v2.jokeapi.dev"

val dataModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single{provideApi(get())}
    single { provideSettingsRepository(get(),get()) }
    single { provideJokesListRepositoryImpl(get(),get(),get()) }
    factory <JokesListRepository> { JokesListRepositoryImpl(get(),get(),get()) }
    factory<IJokesListUseCase> { JokesListUseCase(get()) }
    factory<SendJoke> { SendJokeImpl(get()) }
    factory<SettingsRepository> { SettingsRepositoryImpl(get(),get()) }
}


fun provideRetrofit(okhttp: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhttp)
        .build()
}


fun provideApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}


fun provideOkHttpClient() = OkHttpClient.Builder().build()


fun provideSettingsRepository(
    context: Context,
    showMessage: ShowMessage
): SettingsRepositoryImpl {
    return SettingsRepositoryImpl(context, showMessage)
}


fun provideJokesListRepositoryImpl(
    jokesApiService: ApiService,
    context: Context,
    message: ShowMessage
): JokesListRepositoryImpl {
    return JokesListRepositoryImpl(jokesApiService, context, message)
}




