package com.fatalzero.rsandroidfinal_task.utils.dialog


class DialogService(private var dialogFactory: DialogFactory) {
    fun showDeleteDialog(action:()->Unit){
        dialogFactory.getDeleteDialog{
            action.invoke()
        }?.show()
    }
}