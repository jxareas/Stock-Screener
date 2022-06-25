package com.jxareas.stonks.data.repository

import com.jxareas.stonks.data.csv.CompanyListingsParser
import com.jxareas.stonks.data.csv.CsvParser
import com.jxareas.stonks.data.local.database.StocksDatabase
import com.jxareas.stonks.data.local.entity.CompanyListingEntity
import com.jxareas.stonks.data.mapper.toCompanyListing
import com.jxareas.stonks.data.mapper.toCompanyListingEntity
import com.jxareas.stonks.data.remote.StocksService
import com.jxareas.stonks.domain.model.CompanyListing
import com.jxareas.stonks.domain.repository.StocksRepository
import com.jxareas.stonks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StocksService,
    val db: StocksDatabase,
    val companyListingsParser: CsvParser<CompanyListing>,
) :
    StocksRepository {

    private val dao = db.companyListingDao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>> = flow {

        emit(Resource.Loading(true))
        val localListings = dao.searchCompanyListings(query)
        emit(Resource.Success(
            data = localListings.map(CompanyListingEntity::toCompanyListing)
        ))

        val isDbEmpty = query.isBlank() && localListings.isEmpty()
        val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
        if (shouldLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }

        val remoteListings = try {
            val response = api.getListings()
            companyListingsParser.parse(response.byteStream())
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load the data"))
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load the data"))
            null
        }

        remoteListings?.let { listings ->
            dao.deleteAll()
            dao.insertAll(listings.map(CompanyListing::toCompanyListingEntity))
            emit(
                Resource.Success(
                    data = dao
                        .searchCompanyListings("")
                        .map(CompanyListingEntity::toCompanyListing))
            )
            emit(Resource.Loading(false))
        }


    }


}