package com.example.msigl62.coworkandroiduset.extension

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import com.example.msi_gl62.co_work_android_uset.R
import android.content.DialogInterface
import android.content.Intent
import com.example.msigl62.coworkandroiduset.ui.login.LoginActivity

object DialogUtil {
    fun showDialogMessage(context: Context, @StringRes message: Int) {
        showDialogMessage(context, context.getString(message))
    }

    private fun showDialogMessage(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog))
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.dialog_button), null)
                .setNegativeButton(null, null)

        builder.setPositiveButton(R.string.dialog_button, DialogInterface.OnClickListener { dialog, id ->
            val intent = Intent(context, LoginActivity::class.java)
            (context).startActivity(intent)
        })

        builder.show()
    }
}