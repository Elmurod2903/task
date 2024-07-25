package uz.elmurod.domain.ports.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import uz.elmurod.domain.data.database.ArticleEntity
import uz.elmurod.domain.data.ui.Articles


interface ArticlesDatabasePort {
    suspend fun insert(item: ArticleEntity)

    suspend fun getAllNews(): Flow<List<ArticleEntity>>

    suspend fun deleteArticle(item: ArticleEntity)

    suspend fun checkNews(item: Int):Int
    //

}