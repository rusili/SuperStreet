package com.rusili.superstreet.common.base

import android.accounts.NetworkErrorException
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.R
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val STRING_SHARE_TYPE = "text/plain"
private const val STRING_SHARE_SUBJECT = "Sharing URL"
private const val STRING_SHARE_VIA = "Share via: "

abstract class BaseActivity : AppCompatActivity() {
    protected val disposable = CompositeDisposable()
    var container = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        window.apply {
            enterTransition = null
            exitTransition = null
        }
        super.onCreate(savedInstanceState)

        container = R.id.activityFragmentContainer
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }

    protected fun inflateFragment(fragment: BaseFragment) =
        supportFragmentManager.beginTransaction()
            .add(container, fragment)
            .commit()

    protected fun internalShareLink(link: String) {
        startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    setType(STRING_SHARE_TYPE)
                    putExtra(Intent.EXTRA_SUBJECT, STRING_SHARE_SUBJECT)
                    putExtra(Intent.EXTRA_TEXT, link)
                },
                STRING_SHARE_VIA
            )
        )
    }

    protected fun showError(error: Throwable?) {
        when (error) {
            is IntentSender.SendIntentException -> showErrorDialogToFinish()
            is NetworkErrorException -> showNetworkError()
            is UnknownHostException -> showNetworkError()
            is SocketTimeoutException -> showNetworkError()
            else -> showUnknownError()
        }
    }

    protected fun showSnackbar(
        message: String,
        length: Int = 0
    ) {
        window?.let {
            it.decorView.rootView?.let {
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
