package com.rusili.superstreet.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rusili.superstreet.R

class MainActivity : AppCompatActivity(), MainNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
