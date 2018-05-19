package com.rusili.superstreet.ui.common

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.View
import com.rusili.superstreet.ui.MainNavigator

abstract class BaseFragment : Fragment() {
    open lateinit var navigator: MainNavigator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigator = context as MainNavigator
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPresenter()
    }

    abstract fun startPresenter()
}