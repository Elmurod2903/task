package uz.elmurod.domain.mapper

import uz.elmurod.core.mapper.ResponseModelMapper
import uz.elmurod.domain.data.network.ResponseArticles
import uz.elmurod.domain.data.ui.Articles
import javax.inject.Inject

class ArticlesRMMapper @Inject constructor(
    private val sourceRMMapper: SourceRMMapper
) : ResponseModelMapper<ResponseArticles, Articles> {
    override fun mapFromResponse(response: ResponseArticles): Articles = Articles(

        author = response.author,
        title = response.title,
        content = response.content,
        description = response.description,
        publishedAt = response.publishedAt,
        url = response.url,
        urlToImage = response.urlToImage,
        id = response.id
    )

}