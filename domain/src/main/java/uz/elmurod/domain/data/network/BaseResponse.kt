package uz.elmurod.domain.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val status: String?=null,
    val totalResults: Int?=null,
    val articles: List<T>
)