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
import uz.elmurod.main.databinding.FragmentScienceBinding

@AndroidEntryPoint
class ScienceFragment : BaseFragment(R.layout.fragment_science) {
    private val binding: FragmentScienceBinding by viewBinding(FragmentScienceBinding::bind)
    private val viewModel: NewsViewModel by viewModels()
    private val adapter = NewsCategoryAdapter()

    override fun setup() {
        super.setup()
        lifecycleScope.launchWhenResumed {
            binding.recyclerScience.setHasFixedSize(true)
            binding.recyclerScience.adapter = adapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { adapter.refresh() },
                footer = NewsLoadStateAdapter { adapter.retry() }
            )
            binding.btnTry.setOnClickListener {
                adapter.retry()
            }
            adapter.addLoadStateListener { loadState ->
                binding.apply {
                    loadStateProgress.isVisible = loadState.source.refresh is LoadState.Loading
                    recyclerScience.isVisible = loadState.source.refresh is LoadState.Loading
                    btnTry.isVisible = loadState.source.refresh is LoadState.Error
                    errorMassage.isVisible = loadState.source.refresh is LoadState.Error
                    // not found
                    if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1
                    ) {
                        recyclerScience.isVisible = false
                        tvNotFound.isVisible = true

                    } else {
                        recyclerScience.isVisible = true
                        tvNotFound.isVisible = false

                    }
                }
            }
            viewModel.getNewsCategory("science")
            viewModel.getNetworkStatus()
            adapter.onOrderClickListener = OnActionListener {
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
                adapter.submitData(lifecycle, it)
            })
        }
    }

    private val networkStateObserver = Observer<Boolean> { isClicked ->
        if (isClicked) {
            viewModel.getNewsCategory("science")
        }
    }


}