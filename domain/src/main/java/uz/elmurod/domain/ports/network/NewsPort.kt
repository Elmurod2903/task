package uz.elmurod.domain.ports.network

import uz.elmurod.domain.data.network.BaseResponse
import uz.elmurod.domain.data.network.ResponseArticles

interface NewsPort {

    suspend fun searchEverything(
        searchQuery: String,
        pageNUmber: Int
    ): BaseResponse<ResponseArticles>

    suspend fun getNewsCategory(
        countryCode: String,
        pageNUmber: Int
    ): BaseResponse<ResponseArticles>


}