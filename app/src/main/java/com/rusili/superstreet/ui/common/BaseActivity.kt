package com.rusili.superstreet.ui.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
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

    fun <T> GoToActivity(clazz: Class<T>,
                         value: String? = null) {
        val intent = Intent(this, clazz)
        intent.putExtra(BUNDLE_KEY, value)
        startActivity(intent)
    }

    fun inflateFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction()
//            .setCustomAnimations()
                    .replace(container, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()
}