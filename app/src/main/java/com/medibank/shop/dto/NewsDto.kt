package com.medibank.shop.dto

import java.lang.Exception

open class BaseNewsResponse {
    var status = ""
    var totalResults = 0
    var messages = ""
}

data class NewsResponse(
    val articles: ArrayList<NewsDto>
) : BaseNewsResponse()

data class NewsDto(
    val source: SourcesDto? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    val isSelected: Boolean = false,
    val isSaved: Boolean = false
)

data class SourcesDto(
    val id: String?,
    val name: String? = null
)

class NewsResponseException(val status: Int, val msg: String) :
    Exception("status: $status msg: $msg")
