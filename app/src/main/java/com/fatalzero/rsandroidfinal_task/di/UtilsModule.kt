package com.fatalzero.rsandroidfinal_task.di

import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogFactory
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import org.koin.dsl.module

val utilsModule = module{
    factory { DialogFactory(get())}
    factory { DialogService (get()) }
}