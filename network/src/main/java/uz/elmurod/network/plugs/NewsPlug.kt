package uz.elmurod.network.plugs

import uz.elmurod.domain.data.network.BaseResponse
import uz.elmurod.domain.data.network.ResponseArticles
import uz.elmurod.domain.ports.network.NewsPort
import uz.elmurod.network.api.NewsService
import javax.inject.Inject

class NewsPlug @Inject constructor(private val service: NewsService) : NewsPort {

    override suspend fun searchEverything(
        searchQuery: String,
        pageNumber: Int
    ): BaseResponse<ResponseArticles> {
        return service.searchEverything(searchQuery, pageNumber)
    }

    override suspend fun getNewsCategory(
        countryCode: String,
        pageNUmber: Int
    ): BaseResponse<ResponseArticles> {
        return service.getNewsCategory(countryCode, pageNUmber)
    }


}