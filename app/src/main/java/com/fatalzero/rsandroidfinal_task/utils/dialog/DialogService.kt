package com.fatalzero.rsandroidfinal_task.utils.dialog

import javax.inject.Inject

class DialogService @Inject constructor(var dialogFactory: DialogFactory) {
    fun showDeleteDialog(action:()->Unit){
        dialogFactory.getDeleteDialog{
            action.invoke()
        }?.show()
    }
}