package uz.elmurod.main.fragments.category

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.elmurod.core.base.BaseFragment
import uz.elmurod.core.components.OnActionListener
import uz.elmurod.core.util.Constants
import uz.elmurod.domain.viewmodel.NewsViewModel
import uz.elmurod.main.MainFragmentDirections
import uz.elmurod.main.R
import uz.elmurod.main.adapter.NewsCategoryAdapter
import uz.elmurod.main.adapter.NewsLoadStateAdapter
import uz.elmurod.main.databinding.FragmentBusinessBinding

@AndroidEntryPoint
class BusinessFragment : BaseFragment(R.layout.fragment_business) {
    private val binding: FragmentBusinessBinding by viewBinding(FragmentBusinessBinding::bind)
    private val viewModel: NewsViewModel by viewModels()
    private val newsBusinessAdapter = NewsCategoryAdapter()

    override fun setup() {
        super.setup()
        lifecycleScope.launchWhenResumed {

            binding.recyclerBusiness.setHasFixedSize(true)
            binding.recyclerBusiness.adapter = newsBusinessAdapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { newsBusinessAdapter.retry() },
                footer = NewsLoadStateAdapter { newsBusinessAdapter.retry() }

            )
            binding.btnTry.setOnClickListener {
                newsBusinessAdapter.retry()
            }
            newsBusinessAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    loadStateProgress.isVisible = loadState.source.refresh is LoadState.Loading
                    recyclerBusiness.isVisible = loadState.source.refresh is LoadState.Loading
                    btnTry.isVisible = loadState.source.refresh is LoadState.Error
                    errorMassage.isVisible = loadState.source.refresh is LoadState.Error
                    // not found
                    if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        newsBusinessAdapter.itemCount < 1
                    ) {
                        recyclerBusiness.isVisible = false
                        tvNotFound.isVisible = true

                    } else {
                        recyclerBusiness.isVisible = true
                        tvNotFound.isVisible = false

                    }
                }

            }
            viewModel.getNewsCategory("business")
            viewModel.getNetworkStatus()

            newsBusinessAdapter.onOrderClickListener = OnActionListener {
                Bundle().apply {
                    putSerializable(Constants.ARTICLE_KEY, it)
                }
                navigate(MainFragmentDirections.actionMainFragmentToArticlesFragment(it))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.networkState.observe(viewLifecycleOwner, networkStateObserver)

        lifecycleScope.launch {
            viewModel.getNewsCategory.observe(viewLifecycleOwner, Observer {
                newsBusinessAdapter.submitData(lifecycle, it)
            })
        }
    }

    private val networkStateObserver = Observer<Boolean> { isClicked ->
        if (isClicked) {
            viewModel.getNewsCategory("business")
        }
    }


}