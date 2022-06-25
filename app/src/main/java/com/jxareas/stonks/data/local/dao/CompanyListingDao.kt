package com.jxareas.stonks.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jxareas.stonks.data.local.entity.CompanyListingEntity

@Dao
interface CompanyListingDao : BaseDao<CompanyListingEntity> {

    @Query("""
        SELECT * FROM CompanyListing
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == symbol
    """)
    suspend fun searchCompanyListings(query : String) : List<CompanyListingEntity>

    @Query("DELETE FROM CompanyListing")
    suspend fun deleteAll()


}