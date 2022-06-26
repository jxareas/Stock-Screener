package com.jxareas.stonks.di

import com.jxareas.stonks.data.csv.CompanyListingsParser
import com.jxareas.stonks.data.csv.CsvParser
import com.jxareas.stonks.data.repository.StockRepositoryImpl
import com.jxareas.stonks.domain.model.CompanyListing
import com.jxareas.stonks.domain.repository.StocksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(companyListingsParser: CompanyListingsParser):
            CsvParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(stockRepository: StockRepositoryImpl): StocksRepository
}