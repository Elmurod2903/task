package uz.elmurod.domain.data.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class Articles(
    var id: Int? = null,
    val author: String? = null,
    val title: String?=null,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
):Serializable {
    companion object {
        object Comparator : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(
                oldItem: Articles,
                newItem: Articles
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}