package uz.elmurod.search.ui.fragment

import android.webkit.WebViewClient
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.elmurod.core.base.BaseFragment
import uz.elmurod.search.R
import uz.elmurod.search.databinding.ItemFragmentSearchBinding

@AndroidEntryPoint
class ItemSearchFragment : BaseFragment(R.layout.item_fragment_search) {
    private val binding: ItemFragmentSearchBinding by viewBinding(ItemFragmentSearchBinding::bind)
    private val args: ItemSearchFragmentArgs by navArgs()

    override fun setup() {
        super.setup()
        val article = args.article
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        lifecycleScope.launch {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }
    }

}