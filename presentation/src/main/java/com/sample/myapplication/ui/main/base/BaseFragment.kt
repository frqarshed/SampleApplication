package com.sample.myapplication.ui.main.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.sample.myapplication.MainView

abstract class BaseFragment<T : ViewBinding, Q : BaseViewModel> : Fragment(), MainView {

    lateinit var viewBinding: T

    abstract fun setupViewBinding(view: View): T

    abstract fun setupViewModel(): Q

    abstract val layoutId: Int

    private lateinit var mainView: MainView

    lateinit var viewModel: Q

    var isInternetConnected: Boolean = false

    abstract fun onViewCreated(inflater: LayoutInflater, view: View, savedInstanceState: Bundle?)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainView = activity as MainView
    }

    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding = setupViewBinding(view)
        viewModel = setupViewModel()
        viewModel.initialize(requireContext())
        onViewCreated(layoutInflater, view, savedInstanceState)
        initializeLoader()
    }

    private fun initializeLoader() {
        val loaderObserver = Observer<Boolean> { value ->
            if (value) {
                showLoader()
            } else {
                hideLoader()
            }
        }
        viewModel.loader.observe(viewLifecycleOwner, loaderObserver)

        viewModel.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showLoader() {
        mainView.showLoader()
    }

    override fun hideLoader() {
        mainView.hideLoader()
    }

}