package com.sample.myapplication

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity(), MainView {
    override fun showLoader() {

    }

    override fun hideLoader() {

    }

}