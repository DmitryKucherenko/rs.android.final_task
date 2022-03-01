package com.fatalzero.rsandroidfinal_task.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.WindowManager
import androidx.core.content.ContextCompat.startActivity
import com.fatalzero.rsandroidfinal_task.R
import javax.inject.Inject

class DialogFactory @Inject constructor(var context: Context) {

    private val isCanDrawOverLays: Boolean
        get() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) true else
            Settings.canDrawOverlays(context)

    private fun getDialog(message: String) =
        AlertDialog.Builder(context)
            .setNegativeButton(android.R.string.cancel) { _, _ ->
            }
            .setPositiveButton(android.R.string.ok) { _, _ ->
            }
            .setMessage(message)

    fun requestOverlayPermission() {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.packageName)
        ).apply {
            addFlags(FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(context, intent, null)
    }


    fun getDeleteDialog(action: () -> Unit): AlertDialog? =
        if (!isCanDrawOverLays) {
            requestOverlayPermission()
            null
        } else {
            getDialog(context.getString(R.string.Delete_Message)).setPositiveButton(android.R.string.ok) { _, _ ->
                action()
            }.create()
                .apply {
                    window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                }
        }
}
