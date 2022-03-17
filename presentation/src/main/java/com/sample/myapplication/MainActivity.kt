package com.sample.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.data.core.extension.gone
import com.sample.data.core.extension.visible
import com.sample.myapplication.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView {

    lateinit var viewBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun showLoader() {
        viewBinding.progressBar.visible()
    }

    override fun hideLoader() {
        viewBinding.progressBar.gone()
    }

}