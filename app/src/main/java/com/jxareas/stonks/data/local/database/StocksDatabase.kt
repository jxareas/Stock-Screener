package com.jxareas.stonks.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jxareas.stonks.data.local.dao.CompanyListingDao
import com.jxareas.stonks.data.local.entity.CompanyListingEntity

@Database(entities = [CompanyListingEntity::class],
    version = StocksDatabase.DATABASE_VERSION)
abstract class StocksDatabase : RoomDatabase() {

    abstract val companyListingDao: CompanyListingDao

    companion object {
        internal const val DATABASE_VERSION = 1
        internal const val DATABASE_NAME = "stonks.db"
    }

}