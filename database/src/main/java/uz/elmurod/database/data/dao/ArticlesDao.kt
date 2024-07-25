package uz.elmurod.database.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.elmurod.domain.data.database.ArticleEntity
import uz.elmurod.domain.data.ui.Articles

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ArticleEntity)

    @Query("SELECT *FROM article_entity")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("select count(*)  from article_entity where id=:id ")
    suspend fun checkNews(id: Int): Int

    @Delete
    suspend fun deleteArticle(id: ArticleEntity)


}