package uz.elmurod.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.elmurod.core.base.BaseViewHolder
import uz.elmurod.core.components.OnActionListener
import uz.elmurod.domain.data.database.ArticleEntity
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.main.adapter.viewholder.NewsCategoryItemVH
import uz.elmurod.main.adapter.viewholder.NewsSavedItemVH
import uz.elmurod.main.databinding.ItemCategoryBinding

class NewsSavedAdapter :
    ListAdapter<ArticleEntity, BaseViewHolder<ArticleEntity>>(ArticleEntity.Companion.Comparator) {
    var clickListener: OnActionListener<ArticleEntity>? = null

//    private val differCallback = object : DiffUtil.ItemCallback<ArticleEntity>() {
//        override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//    val differ = AsyncListDiffer(this, differCallback)
//
//    inner class NewsSavedVH(private val binding: ItemCategoryBinding, val context: Context) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(articleEntity: ArticleEntity) {
//            binding.apply {
//                title.text = articleEntity.title
//                publishedAt.text = articleEntity.publishedAt
//                Glide.with(context).load(articleEntity.urlToImage)
//                    .centerCrop()
//                    .error(uz.elmurod.core.R.drawable.not_image_url)
//                    .into(urlToImage)
//
//                root.setOnClickListener {
//                    clickListener?.onClick(articleEntity)
//                }
//            }
//
//        }
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArticleEntity> {
        return NewsSavedItemVH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {
            clickListener?.onClick(it)
        }

    }

//    override fun onBindViewHolder(holder: NewsSavedVH, position: Int) {
//        holder.bind(differ.currentList[position])
//    }
//
//    override fun getItemCount(): Int = differ.currentList.size


    override fun onBindViewHolder(holder: BaseViewHolder<ArticleEntity>, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

}