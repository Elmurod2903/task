package uz.elmurod.domain.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ResponseArticles(
    var id: Int? = null,
    @Json(name = "source")
    val source: ResponseSource,
    @Json(name = "author")
    val author: String? = null,
    @Json(name = "title")
    val title:String?=null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: String? = null,
    @Json(name = "publishedAt")
    val publishedAt: String? = null,
    @Json(name = "content")
    val content: String? = null
):Serializable