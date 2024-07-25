package uz.elmurod.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.elmurod.core.base.BaseFragment
import uz.elmurod.core.components.OnActionListener
import uz.elmurod.core.util.Constants
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.domain.viewmodel.NewsViewModel
import uz.elmurod.main.adapter.NewsCategoryAdapter
import uz.elmurod.main.adapter.NewsSavedAdapter
import uz.elmurod.main.databinding.FragmentSavedBinding

@AndroidEntryPoint
class SavedFragment : BaseFragment(R.layout.fragment_saved) {
    private val binding: FragmentSavedBinding by viewBinding(FragmentSavedBinding::bind)
    private val viewModel: NewsViewModel by viewModels()
    private val adapter = NewsSavedAdapter()

    override fun setup() {
        super.setup()
        lifecycleScope.launchWhenResumed {
            binding.recyclerSaved.setHasFixedSize(true)
            binding.recyclerSaved.adapter = adapter
            viewModel.getNewsSaved()

            adapter.clickListener = OnActionListener { articleEntity
                ->
                val articles = Articles(
                    articleEntity.id,
                    articleEntity.author,
                    articleEntity.title,
                    articleEntity.description,
                    articleEntity.url,
                    articleEntity.urlToImage,
                    articleEntity.publishedAt,
                    articleEntity.content
                )
                navigate(SavedFragmentDirections.actionSavedFragmentToArticlesFragment(articles))
            }
            ItemTouchHelper(itemTouchHelperCallback).apply {
                attachToRecyclerView(binding.recyclerSaved)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNewsSaved.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.bindingAdapterPosition
            val articles = adapter.currentList[position]
            viewModel.deleteArticle(articles)

            Snackbar.make(view!!, getString(R.string.delete_article), Snackbar.LENGTH_LONG).apply {
                setAction(getString(R.string.undo)) {
                    viewModel.saveArticle(
                        Articles(
                            articles.id,
                            articles.author,
                            articles.title,
                            articles.description,
                            articles.url,
                            articles.urlToImage,
                            articles.publishedAt,
                            articles.content
                        )
                    )
                }
            }.show()

        }

    }

}