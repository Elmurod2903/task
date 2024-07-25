package uz.elmurod.domain.data.database

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.domain.data.ui.Source
import java.io.Serializable

@Entity(tableName = "article_entity")
@Parcelize
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) : Serializable, Parcelable {
    companion object {
        object Comparator : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ArticleEntity,
                newItem: ArticleEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

