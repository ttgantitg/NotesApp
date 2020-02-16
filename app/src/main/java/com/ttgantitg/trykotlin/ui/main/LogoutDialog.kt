package com.ttgantitg.trykotlin.ui.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ttgantitg.trykotlin.R

class LogoutDialog : DialogFragment() {

    companion object {
        val TAG = LogoutDialog::class.java.name + "TAG"
        fun createInstance() = LogoutDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(context)
            .setTitle(R.string.log_out)
            .setMessage(getString(R.string.logout_dialog_message))
            .setPositiveButton(getString(R.string.logout_dialog_yes)) { _, _ -> (activity as LogoutListener).onLogout()}
            .setNeutralButton(getString(R.string.logout_dialog_no)) { _, _ -> dismiss()}
            .create()!!

    interface LogoutListener {
        fun onLogout()
    }
}