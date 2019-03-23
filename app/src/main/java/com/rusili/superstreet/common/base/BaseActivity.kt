package com.rusili.superstreet.common.base

import android.accounts.NetworkErrorException
import android.content.IntentSender
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.R
import dagger.android.AndroidInjection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseActivity : AppCompatActivity() {
    var container = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        window.apply {
            enterTransition = null
            exitTransition = null
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        super.onCreate(savedInstanceState)

        container = R.id.activityFragmentContainer
    }

    fun inflateFragment(fragment: BaseFragment) =
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()

    fun showError(error: Throwable?) {
        when (error) {
            is IntentSender.SendIntentException -> showErrorDialogToFinish()
            is NetworkErrorException -> showNetworkError()
            is UnknownHostException -> showNetworkError()
            is SocketTimeoutException -> showNetworkError()
            else -> showUnknownError()
        }
    }

    fun showSnackbar(
        message: String,
        length: Int = 0
    ) {
        window?.let {
            it.decorView.rootView.let {
                Snackbar.make(it, message, length).show()
            }
        }
    }

    private fun showErrorDialogToFinish() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.error_generic)
            setPositiveButton(R.string.ok) { _, _ ->
                getWindow().setExitTransition(null)
                finish()
            }
        }.also {
            it.create().show()
        }
    }

    private fun showUnknownError() = showSnackbar(getString(R.string.error_generic))

    private fun showNetworkError() = showSnackbar(getString(R.string.error_no_internet))
}
