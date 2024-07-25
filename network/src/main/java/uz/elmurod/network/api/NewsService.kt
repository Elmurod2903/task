package uz.elmurod.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.elmurod.domain.data.network.BaseResponse
import uz.elmurod.domain.data.network.ResponseArticles
import uz.elmurod.network.util.Constants
import uz.elmurod.network.util.Endpoints

interface NewsService {

    @GET(Endpoints.everything)
    suspend fun searchEverything(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.apikey
    ): BaseResponse<ResponseArticles>

    @GET(Endpoints.top_headlines)
    suspend fun getNewsCategory(
        @Query("category")
        categoryCode: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.apikey
    ): BaseResponse<ResponseArticles>

}