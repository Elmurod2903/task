package uz.elmurod.database.plugs

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import uz.elmurod.database.data.dao.ArticlesDao
import uz.elmurod.domain.data.database.ArticleEntity
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.domain.ports.database.ArticlesDatabasePort
import javax.inject.Inject

class ArticlePlugDatabase @Inject constructor(
    private val dao: ArticlesDao,
) : ArticlesDatabasePort {
    override suspend fun insert(item: ArticleEntity) {
        return dao.insert((item))
    }

    override suspend fun getAllNews(): Flow<List<ArticleEntity>> {
        return dao.getAllArticles()
    }

    override suspend fun deleteArticle(item: ArticleEntity) {
        return dao.deleteArticle((item))
    }

    override suspend fun checkNews(item: Int): Int {
        return dao.checkNews(item)
    }

}