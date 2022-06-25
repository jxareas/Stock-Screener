package com.jxareas.stonks.domain.repository

import com.jxareas.stonks.domain.model.CompanyListing
import com.jxareas.stonks.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StocksRepository {

    suspend fun getCompanyListings(fetchFromRemote: Boolean, query: String):
            Flow<Resource<List<CompanyListing>>>

}