package com.medibank.shop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medibank.shop.R
import com.medibank.shop.adapters.SourcesAdapter
import com.medibank.shop.base.BaseNewsFragment
import com.medibank.shop.base.BaseNewsViewModel
import com.medibank.shop.databinding.FragmentSourcesBinding
import com.medibank.shop.model.NewsData
import com.medibank.shop.model.SourcedsData
import com.medibank.shop.utils.showAlertDialog
import com.medibank.shop.viewmodel.SourcesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment : BaseNewsFragment() {

    private val viewModel: SourcesViewModel by viewModels()

    // baseViewModel is being observed by base fragment to handle errors
    override val baseViewModel: BaseNewsViewModel
        get() = viewModel

    private var mSources: List<SourcedsData>? = null
    private lateinit var binding: FragmentSourcesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.sourcesList.observe(viewLifecycleOwner, Observer { items ->

            mSources = items
            setupRv(binding.rvSources, items)
        })

        viewModel.apiError.observe(viewLifecycleOwner, {
            showAlertDialog(
                requireContext(),
                getString(R.string.error),
                it
            )
        })
    }

    private fun setupRv(rv: RecyclerView, news: List<SourcedsData>) {
        with(rv) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = SourcesAdapter(news)
        }
    }

    override fun onResume() {
        viewModel.refreshSources()
        super.onResume()
    }
}