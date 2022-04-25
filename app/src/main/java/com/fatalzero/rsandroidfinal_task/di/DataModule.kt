package com.fatalzero.rsandroidfinal_task.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.fatalzero.rsandroidfinal_task.data.network.ApiService
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.data.repository.SettingsRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.usecase.AddFilterUseCase
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage

@Module
class DataModule {
    private val BASE_URL = "https://v2.jokeapi.dev"

    @Singleton
    @Provides
    fun provideRetrofit(okhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okhttp)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideSettingsRepository(
        context: Context,
        showMessage: ShowMessage
    ): SettingsRepositoryImpl {
        return SettingsRepositoryImpl(context, showMessage)
    }

    @Singleton
    @Provides
    fun provideJokesListRepositoryImpl(
        jokesApiService: ApiService,
        context: Context,
        message: ShowMessage
    ): JokesListRepositoryImpl {
        return JokesListRepositoryImpl(jokesApiService, context, message)
    }

    @Provides
    fun provideAddFilterUseCase(repository:JokesListRepositoryImpl): AddFilterUseCase {
        return AddFilterUseCase(repository)
    }


}