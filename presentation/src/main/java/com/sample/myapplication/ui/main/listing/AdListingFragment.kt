package com.sample.myapplication.ui.main.listing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.data.core.extension.gone
import com.sample.data.core.extension.visible
import com.sample.myapplication.R
import com.sample.myapplication.databinding.ListingFragmentBinding
import com.sample.myapplication.ui.main.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdListingFragment : BaseFragment<ListingFragmentBinding, AdListingViewModel>() {

    override val layoutId: Int
        get() = R.layout.listing_fragment

    override fun setupViewBinding(view: View): ListingFragmentBinding =
        ListingFragmentBinding.bind(view)

    override fun setupViewModel(): AdListingViewModel {
        val viewModel: AdListingViewModel by viewModels()
        return viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort -> {
                viewModel.sort()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(inflater: LayoutInflater, view: View, savedInstanceState: Bundle?) {
        val adapter = AdsAdapter(requireContext(), OnClickListener { ad, _ ->
            findNavController().navigate(
                directions = AdListingFragmentDirections.actionAdListingFragmentToAdDetailsJavaFragment(ad)
            )
        })

        viewBinding.list.layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.list.adapter = adapter

        var isDataLoaded = false
        viewModel.ads.observe(viewLifecycleOwner) {
            isDataLoaded = true
            setHasOptionsMenu(true)
            adapter.setAds(it)
        }

        viewModel.connectivityLiveData.observe(viewLifecycleOwner) {
            if (!it && !isDataLoaded) {
                viewModel.hideLoader()
                viewBinding.list.gone()
                viewBinding.internet.main.visible()
            } else {
                if (!isDataLoaded) {
                    viewModel.loadData()
                }
                viewBinding.list.visible()
                viewBinding.internet.main.gone()
            }
        }
    }
}