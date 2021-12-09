package com.fatalzero.rsandroidfinal_task.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingSource
import com.fatalzero.rsandroidfinal_task.domain.GetSettingsUseCase
import com.fatalzero.rsandroidfinal_task.domain.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.domain.SaveSettingsUseCase
import com.fatalzero.rsandroidfinal_task.presentation.adapter.JokePagingSource
import com.fatalzero.rsandroidfinal_task.presentation.viewModel.JokesListViewModel
import com.fatalzero.rsandroidfinal_task.presentation.viewModel.MainActivityViewModel
import java.lang.RuntimeException
import javax.inject.Inject

//class ViewModelFactory @Inject constructor(
//    private val jokeSendUseCase: JokeSendUseCase,
//    private val jokePagingSource: JokePagingSource,
//    private val  getSettingsUseCase: GetSettingsUseCase,
//    private val saveSettingUseCase: SaveSettingsUseCase
//): ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//      if(modelClass==JokesListViewModel::class.java){
//          return JokesListViewModel(jokeSendUseCase,jokePagingSource) as T
//      }else
//          if(modelClass==MainActivityViewModel::class.java){
//              return MainActivityViewModel(getSettingsUseCase,saveSettingUseCase) as T
//          }
//        throw RuntimeException("Unknown view model class $modelClass")
//    }
//
//}


class ViewModelFactory @Inject constructor(
   private val viewModels: @JvmSuppressWildcards Map<String,ViewModel>
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return viewModels[modelClass.simpleName] as T
    }

}

