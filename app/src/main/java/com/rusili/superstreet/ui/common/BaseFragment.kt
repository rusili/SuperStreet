package com.rusili.superstreet.ui.common

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.ui.MainNavigator
import dagger.android.support.AndroidSupportInjection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseFragment : Fragment() {
    open lateinit var navigator: MainNavigator

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        navigator = context as MainNavigator
    }

    open fun showError(error: Throwable?) {
        when (error) {
            is UnknownHostException -> showNetworkError()
            is SocketTimeoutException -> showNetworkError()
            else -> showUnknownError()
        }
    }

    private fun showUnknownError() {
        view?.let {
            Snackbar.make(it, "Error", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showNetworkError() {
        view?.let {
            Snackbar.make(it, "No internet connection", Snackbar.LENGTH_LONG).show()
        }
    }
}