package com.rusili.superstreet.ui.common

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rusili.superstreet.R
import dagger.android.AndroidInjection

const val BUNDLE_KEY = "BUNDLE_KEY"

abstract class BaseActivity : AppCompatActivity() {
    var container = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        container = R.id.activityFragmentContainer
    }

    fun inflateFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction()
//            .setCustomAnimations()
                    .replace(container, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()

    fun showError(error: Throwable? = null) {
        // TODO: Show specific error using throwable & generic error
    }

    fun showErrorAndFinish(error: Throwable? = null) {
        showError(error)
        finish()
    }

    fun <T> goToActivity(clazz: Class<T>,
                         value: String? = null) {
        val intent = Intent(this, clazz)
        intent.putExtra(BUNDLE_KEY, value)
        startActivity(intent)
    }
}