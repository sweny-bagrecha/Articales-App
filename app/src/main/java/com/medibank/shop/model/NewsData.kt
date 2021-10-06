package com.medibank.shop.model

import com.medibank.shop.dto.SourcesDto

data class NewsData(
    val source: SourcesDto? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

data class SourcedsData(
    val source: SourcesDto? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    val isSelected: Boolean?,
    val isSaved: Boolean?
)
