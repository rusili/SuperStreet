package com.rusili.superstreet.splash

import android.content.Intent
import android.os.Bundle
import com.rusili.superstreet.home.HomeActivity
import com.rusili.superstreet.R
import com.rusili.superstreet.common.base.BaseActivity

// TODO: Implement
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        toMainActivity()
    }

    private fun toMainActivity() {
        Intent(this, HomeActivity::class.java).apply {
            startActivity(this)
        }
    }
}
