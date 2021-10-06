package com.medibank.shop.api

import com.medibank.shop.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiConstants.ALLDATA_URL)
    suspend fun getNewsList(
        @Query(ApiConstants.SOURCE_ID) sourceId: String): Response<NewsResponse>

    @GET(ApiConstants.ALLDATA_URL)
    suspend fun getSourcesList(): Response<NewsResponse>
}