package uz.elmurod.main.fragments.details

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Paint
import android.net.Uri
import android.webkit.WebChromeClient
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import uz.elmurod.core.base.BaseFragment
import uz.elmurod.domain.data.database.ArticleEntity
import uz.elmurod.domain.viewmodel.NewsViewModel
import uz.elmurod.main.R
import uz.elmurod.main.databinding.FragmentArticlesBinding

@AndroidEntryPoint
class ArticleFragment : BaseFragment(R.layout.fragment_articles) {
    private val binding: FragmentArticlesBinding by viewBinding(FragmentArticlesBinding::bind)
    private val viewModel: NewsViewModel by viewModels()
    private val args: ArticleFragmentArgs by navArgs()

    var isChecked = false

    override fun setup() {
        super.setup()
        val article = args.article

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        lifecycleScope.launch {
            binding.title.text = article.title
            binding.url.text = article.url
            binding.description.text = article.description
            Glide.with(context!!.applicationContext).load(article.urlToImage)
                .centerCrop()
                .error(R.drawable.not_image_url)
                .into(binding.urlToImage)
            article.id?.let { viewModel.checkNews(it) }

            binding.saveOrDeleteBtn.setOnClickListener {
                isChecked = !isChecked
                if (isChecked) {
                    viewModel.saveArticle(article)
                    Snackbar.make(view!!, getString(R.string.save_article), Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.deleteArticle(
                        ArticleEntity(
                            article.id,
                            article.author,
                            article.title,
                            article.description,
                            article.url,
                            article.urlToImage,
                            article.publishedAt,
                            article.content
                        )
                    )
                }
                binding.saveOrDeleteBtn.isChecked = isChecked
            }
            binding.url.setOnClickListener {
                activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.url)))
            }
            binding.url.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.checkNews.observe(viewLifecycleOwner, Observer { count ->
            if (count > 0) {
                binding.saveOrDeleteBtn.isChecked = true
                isChecked = true
            } else {
                binding.saveOrDeleteBtn.isChecked = false
                isChecked = false
            }
        })
    }
}