package com.rusili.superstreet.common.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.R
import dagger.android.support.AndroidSupportInjection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    open fun showError(error: Throwable?) {
        when (error) {
            is NoNetworkException -> showNetworkError()
            is UnknownHostException -> showNetworkError()
            is SocketTimeoutException -> showNetworkError()
            else -> showUnknownError()
        }
    }

    fun showSnackbar(
        message: String,
        length: Int = 0
    ) {
        view?.let {
            Snackbar.make(it, message, length).show()
        }
    }

    private fun showUnknownError() {
        showSnackbar(getString(R.string.error_generic))
    }

    private fun showNetworkError() {
        showSnackbar(getString(R.string.error_no_internet))
    }
}
