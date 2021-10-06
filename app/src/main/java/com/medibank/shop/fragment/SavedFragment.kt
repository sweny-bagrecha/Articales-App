package com.medibank.shop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medibank.shop.R
import com.medibank.shop.adapters.SavedAdapter
import com.medibank.shop.base.BaseNewsFragment
import com.medibank.shop.base.BaseNewsViewModel
import com.medibank.shop.databinding.FragmentSavedBinding
import com.medibank.shop.model.SourcedsData
import com.medibank.shop.utils.showAlertDialog
import com.medibank.shop.viewmodel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : BaseNewsFragment() {

    private val viewModel: SavedViewModel by viewModels()

    // baseViewModel is being observed by base fragment to handle errors
    override val baseViewModel: BaseNewsViewModel
        get() = viewModel

    private var mNews: List<SourcedsData>? = null
    private var mAllNews: List<SourcedsData>? = null

    private lateinit var binding: FragmentSavedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observer
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.newsList.observe(viewLifecycleOwner, Observer { items ->
            mNews = items
            /**
             * Setup recyclerView
             */
            setupRv(binding.rvHeadlines, items)
        })

        viewModel.allNewsList.observe(viewLifecycleOwner, Observer { items ->
            mAllNews = items
            for (i in 0 until items.size-1) {
                //Todo add the check for saved news
                /**
                 * Lets say like : if(items.get(i).isSaved){ //call viewModel.refreshNews(sourceId)}
                 */
                mAllNews?.get(i)?.source?.id?.let { viewModel.refreshNews(it) }
            }
        })

        viewModel.apiError.observe(viewLifecycleOwner, {
            showAlertDialog(
                requireContext(),
                getString(R.string.error),
                it
            )
        })

        viewModel.eventURLSelected.observe(viewLifecycleOwner, { url ->
            val action = SavedFragmentDirections.actionSavedToWeb(url)
            findNavController().navigate(action)
        })
    }

    /**
     * Attach recylerview with Adapter
     *
     * @param rv : recyclerview
     * @param news : List of SourcedData
     */
    private fun setupRv(rv: RecyclerView, news: List<SourcedsData>) {
        with(rv) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = SavedAdapter(news,viewModel)
        }
    }

    override fun onResume() {
        /**
         * On screen, reload respective refreshed news
         */
        viewModel.refreshAllNews()
        super.onResume()
    }

}