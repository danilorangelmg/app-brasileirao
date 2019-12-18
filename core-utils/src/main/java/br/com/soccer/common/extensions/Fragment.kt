package br.com.soccer.common.extensions

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import br.com.soccer.common.R

fun Fragment.showErrorDialogTryAgain(message: String, tryAgainFunction: () -> Unit) {
    context?.let { AlertDialog.Builder(it) }?.apply {
        setTitle(context?.getString(R.string.dialog_error_title))
        setMessage(message)
        setCancelable(true)
        setPositiveButton(context?.getString(R.string.dialog_error_try_again_button))
        { dialog, _ ->
            dialog.cancel()
            tryAgainFunction()
        }
    }?.let { it.create().show() }
}