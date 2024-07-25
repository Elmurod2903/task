package uz.elmurod.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.domain.mapper.ArticlesRMMapper
import uz.elmurod.domain.ports.database.ArticlesDatabasePort
import uz.elmurod.domain.ports.network.NewsPort
import java.io.IOException

private const val START_PAGE_INDEX = 1

class NewsCategoryPagingDataSource(
    private val newsPort: NewsPort,
    private val articlesRMMapper: ArticlesRMMapper,
    private val categoryCode: String,
) : PagingSource<Int, Articles>() {
    override fun getRefreshKey(state: PagingState<Int, Articles>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        val position = params.key ?: START_PAGE_INDEX
        return try {
            val response = newsPort.getNewsCategory(categoryCode, position)
            val news = articlesRMMapper.mapFromResponseList(response.articles)

            LoadResult.Page(
                data = news,
                prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                nextKey = if (news.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}