package com.jxareas.stonks.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksService {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apiKey") apiKey: String = StocksService.apiKey,
    ): ResponseBody

    companion object {
        const val apiKey = "2QNA60V0C7L3Q2OY"
        const val BASE_URL = "https://alphavantage.co"
    }

}