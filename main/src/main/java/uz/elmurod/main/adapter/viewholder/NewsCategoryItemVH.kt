package uz.elmurod.main.adapter.viewholder

import com.bumptech.glide.Glide
import uz.elmurod.core.base.BaseViewHolder
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.main.R
import uz.elmurod.main.databinding.ItemCategoryBinding
import java.text.SimpleDateFormat
import java.util.*


class NewsCategoryItemVH(
    val binding: ItemCategoryBinding,
    val onClick: (articles: Articles) -> Unit
) : BaseViewHolder<Articles>(binding.root) {

    private val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    override fun bind(item: Articles) = with(binding) {
        super.bind(item)
        title.text = item.title
        publishedAt.text =item.publishedAt
        Glide.with(context).load(item.urlToImage)
            .centerCrop()
            .error(uz.elmurod.core.R.drawable.not_image_url)
            .into(urlToImage)
        root.setOnClickListener {
            onClick(item)
        }
    }
}