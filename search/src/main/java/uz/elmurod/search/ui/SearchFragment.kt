package uz.elmurod.search.ui

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.elmurod.core.base.BaseFragment
import uz.elmurod.core.components.OnActionListener
import uz.elmurod.core.util.Constants
import uz.elmurod.search.R
import uz.elmurod.search.databinding.FragmentSearchBinding
import uz.elmurod.domain.viewmodel.NewsViewModel
import uz.elmurod.main.MainFragmentDirections
import uz.elmurod.main.adapter.NewsCategoryAdapter
import uz.elmurod.main.adapter.NewsLoadStateAdapter

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {
    val TIME_DELAY = 500L

    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: NewsViewModel by viewModels()

    private val searchNewsAdapter = NewsCategoryAdapter()

    override fun setup() {
        super.setup()
        binding.search.background = null
        var job: Job? = null

        lifecycleScope.launchWhenResumed {
            binding.recycler.layoutManager = LinearLayoutManager(context)
            binding.recycler.setHasFixedSize(true)
            binding.recycler.adapter = searchNewsAdapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { searchNewsAdapter.refresh() },
                footer = NewsLoadStateAdapter { searchNewsAdapter.retry() }
            )

            binding.search.addTextChangedListener { editable ->
                job?.cancel()

                job = MainScope().launch {
                    delay(TIME_DELAY)
                    editable.let {
                        if (editable.toString().isNotEmpty()) {
                            viewModel.searchEveryThing(editable.toString())
                        }
                    }
                }
            }
            viewModel.getNetworkStatus()
            binding.btnTry.setOnClickListener {
                searchNewsAdapter.retry()
            }
            searchNewsAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    loadStateProgress.isVisible = loadState.source.refresh is LoadState.Loading
                    recycler.isVisible = loadState.source.refresh is LoadState.Loading
                    btnTry.isVisible = loadState.source.refresh is LoadState.Error
                    errorMassage.isVisible = loadState.source.refresh is LoadState.Error
                    // not found
                    if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        searchNewsAdapter.itemCount < 1
                    ) {
                        recycler.isVisible = false
                        tvNotFound.isVisible = true

                    } else {
                        recycler.isVisible=true
                        tvNotFound.isVisible = false
                    }
                }

            }
            searchNewsAdapter.onOrderClickListener = OnActionListener {
                Bundle().apply {
                    putSerializable(Constants.ARTICLE_KEY, it)
                }
                navigate(SearchFragmentDirections.actionSearchFragmentToItemSearchFragment(it))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.networkState.observe(viewLifecycleOwner, networkStateObserver)

        viewModel.searchEveryThing.observe(viewLifecycleOwner, Observer {
            searchNewsAdapter.submitData(lifecycle, it)
        })
    }

    private val networkStateObserver = Observer<Boolean> { isClicked ->
        if (isClicked) {
            viewModel.getNewsCategory("business")
        }
    }

}