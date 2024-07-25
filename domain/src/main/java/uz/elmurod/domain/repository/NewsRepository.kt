package uz.elmurod.domain.repository

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import uz.elmurod.domain.data.database.ArticleEntity
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.domain.mapper.ArticlesRMMapper
import uz.elmurod.domain.paging.NewsCategoryPagingDataSource
import uz.elmurod.domain.paging.SearchPagingDataSource
import uz.elmurod.domain.ports.database.ArticlesDatabasePort
import uz.elmurod.domain.ports.network.NewsPort
import uz.elmurod.domain.ports.preference.PreferencePort
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsPort: NewsPort,
    private val articlesRMMapper: ArticlesRMMapper,
    private val preferencePort: PreferencePort,
    private val dbPort: ArticlesDatabasePort
) {
    fun searchEveryThing(searchQuery: String): Flow<PagingData<Articles>>? {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    maxSize = 100
                ),
                pagingSourceFactory = {
                    SearchPagingDataSource(newsPort, articlesRMMapper, searchQuery)
                }).flow
        } catch (e: Exception) {
            null
        }
    }


    fun getNewsCategory(categoryCode: String): Flow<PagingData<Articles>>? {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    maxSize = 100
                ),
                pagingSourceFactory = {
                    NewsCategoryPagingDataSource(newsPort, articlesRMMapper, categoryCode)
                }).flow
        } catch (e: Exception) {
            null
        }
    }

    fun getNetworkStatus(): Flow<Boolean> = preferencePort.networkStatus()

    suspend fun insertArticle(article: ArticleEntity) = dbPort.insert(article)

    suspend fun getNewsAllSaved() = dbPort.getAllNews()

    suspend fun deleteArticle(article: ArticleEntity) = dbPort.deleteArticle(article)

    suspend fun checkNews(article: Int):Int = dbPort.checkNews(article)
}