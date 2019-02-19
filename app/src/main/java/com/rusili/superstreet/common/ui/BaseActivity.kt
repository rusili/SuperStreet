package com.rusili.superstreet.common.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.R
import dagger.android.AndroidInjection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseActivity : AppCompatActivity() {
    var container = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        window.enterTransition = null
        window.exitTransition = null
        super.onCreate(savedInstanceState)

        container = R.id.activityFragmentContainer
    }

    fun inflateFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction()
//            .setCustomAnimations()
                    .replace(container, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()

    fun showError(error: Throwable?) {
        when (error) {
            is NoIntentException -> showErrorDialogToFinish()
            is NoNetworkException -> showNetworkError()
            is UnknownHostException -> showNetworkError()
            is SocketTimeoutException -> showNetworkError()
            else -> showUnknownError()
        }
    }

    fun showSnackbar(message: String,
                     length: Int = 0) {
        window?.decorView?.rootView?.let {
            Snackbar.make(it, message, length).show()
        }
    }

    private fun showErrorDialogToFinish() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Error")
                .setPositiveButton("Ok") { _, _ ->
                    getWindow().setExitTransition(null)
                    finish()
                };
        builder.create().show()
    }

    private fun showUnknownError() = showSnackbar(getString(R.string.error_generic))

    private fun showNetworkError() = showSnackbar(getString(R.string.error_no_internet))
}