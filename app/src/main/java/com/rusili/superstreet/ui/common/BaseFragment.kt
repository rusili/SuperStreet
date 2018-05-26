package com.rusili.superstreet.ui.common

import android.content.Context
import android.support.v4.app.Fragment
import com.rusili.superstreet.ui.MainNavigator

abstract class BaseFragment
    : Fragment() {
    open lateinit var navigator: MainNavigator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigator = context as MainNavigator
    }
}