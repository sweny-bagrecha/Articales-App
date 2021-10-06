@file:JvmName("UIUtils")
@file:JvmMultifileClass
package com.medibank.shop.utils

import android.app.AlertDialog
import android.content.Context

fun showAlertDialog(context: Context, title: String, message: String) {
    val dialog = AlertDialog.Builder(context)
    dialog.setTitle(title)
    dialog.setMessage(message)
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

    dialog.setPositiveButton(android.R.string.ok){
            dialog, which ->
           dialog.dismiss()
    }
    dialog.show()

}