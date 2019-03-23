package com.rusili.superstreet.image.ui

import android.transition.Transition
import androidx.transition.TransitionListenerAdapter

abstract class SimpleTransitionListenerAdapter : TransitionListenerAdapter(), Transition.TransitionListener {

    override fun onTransitionStart(transition: Transition?) = Unit
    override fun onTransitionResume(transition: Transition?) = Unit
    override fun onTransitionPause(transition: Transition?) = Unit
    override fun onTransitionCancel(transition: Transition?) = Unit
    override fun onTransitionEnd(transition: Transition?) = Unit
}
