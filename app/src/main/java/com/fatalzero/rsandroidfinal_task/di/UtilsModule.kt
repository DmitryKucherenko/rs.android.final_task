package com.fatalzero.rsandroidfinal_task.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatalzero.rsandroidfinal_task.utils.ViewModelFactory
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogFactory
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class UtilsModule {

    @Provides
    fun provideDialogFactory(context: Context): DialogFactory {
        return DialogFactory(context)
    }

    @Provides
    fun provideDialogService(dialogFactory: DialogFactory): DialogService {
        return DialogService(dialogFactory)
    }

    @Provides
    fun provideViewModelFactory(
        viewModels: @JvmSuppressWildcards Map<String, ViewModel>
    ): ViewModelFactory {
        return ViewModelFactory(viewModels)
    }

}