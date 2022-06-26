package com.jxareas.stonks.data.remote.service

import com.jxareas.stonks.data.remote.constants.ApiConstants
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksService {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apiKey") apiKey: String = ApiConstants.apiKey,
    ): ResponseBody

}