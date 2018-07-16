package com.rusili.superstreet.ui.common

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_image.*
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val BUNDLE_KEY = "BUNDLE_KEY"

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

    open fun showError(error: Throwable?) {
        when (error) {
            is NoIntentException -> showErrorDialogToFinish()
            is UnknownHostException -> showNetworkError()
            is SocketTimeoutException -> showNetworkError()
            else -> showUnknownError()
        }
    }

    open fun showSnackbar(message: String,
                          length: Int = -1) {
        window?.decorView?.rootView?.let {
            Snackbar.make(it, message, length).show()
        }
    }

    private fun showErrorDialogToFinish() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Error")
                .setPositiveButton("Ok") { dialog, which ->
                    getWindow().setExitTransition(null)
                    finish()
                };
        builder.create().show()
    }

    private fun showUnknownError() = showSnackbar("Unknown Error")

    private fun showNetworkError() = showSnackbar("No internet connection", Snackbar.LENGTH_LONG)
}