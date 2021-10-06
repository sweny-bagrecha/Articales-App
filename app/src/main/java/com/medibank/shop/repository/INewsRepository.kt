package com.medibank.shop.repository

import com.google.gson.Gson
import com.medibank.shop.api.ApiService
import com.medibank.shop.dto.*
import retrofit2.Response
import javax.inject.Inject

interface INewsRepository {
    suspend fun getNews(sourceId: String): ArrayList<NewsDto>
    suspend fun getSources(): ArrayList<NewsDto>
}

class NewsRepositoryImpl @Inject constructor(
    val apiService: ApiService) : INewsRepository {

    private var allNewsDtoCache = ArrayList<NewsDto>()
    private var newsDtoCache = ArrayList<NewsDto>()

    /**
     * Process api call - helper method to process Api call and extract response body
     *
     * Api errors ae thrown to be processed by view model
     *
     * @param T
     * @param apiCall
     * @receiver
     * @return Response Body
     */
    suspend fun <T> processApiCall(apiCall: suspend () -> Response<T>): T {
        val response: Response<T>
        try {
            response = apiCall()
        } catch (e: Exception) {
            throw NewsResponseException(-1, e.message ?: e.toString())
        }
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            }
        }
        response.errorBody()?.let {
            val ex = parseErrorBody(it.charStream().readText())
            throw  ex
        }
        throw NewsResponseException(response.code(), response.message())
    }

    /**
     * Parse error body - helper method to convert error response into TripResponseException
     *
     * @param errorBody
     */
    private fun parseErrorBody(errorBody: String) =
        try {
            val rsp = Gson().fromJson(errorBody, BaseNewsResponse::class.java)
            NewsResponseException(rsp.totalResults, rsp.messages)
        } catch (e: Exception) {
            NewsResponseException(-1, e.message ?: e.toString())
        }

    override suspend fun getNews(sourceId: String): ArrayList<NewsDto> =
        processApiCall {
            apiService.getNewsList(sourceId)
        }.articles.also {
            //setup local cache
            newsDtoCache = it
        }

    override suspend fun getSources(): ArrayList<NewsDto>  =
        processApiCall {
            apiService.getSourcesList()
        }.articles.also {
            //setup local cache
            allNewsDtoCache = it
        }

}

