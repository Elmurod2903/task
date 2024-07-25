package uz.elmurod.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import uz.elmurod.core.base.BaseViewHolder
import uz.elmurod.core.components.OnActionListener
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.main.adapter.viewholder.NewsCategoryItemVH
import uz.elmurod.main.databinding.ItemCategoryBinding

class NewsCategoryAdapter :
    PagingDataAdapter<Articles, BaseViewHolder<Articles>>(Articles.Companion.Comparator) {
    var onOrderClickListener: OnActionListener<Articles>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Articles> {
        return NewsCategoryItemVH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {
            onOrderClickListener?.onClick(it)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Articles>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}