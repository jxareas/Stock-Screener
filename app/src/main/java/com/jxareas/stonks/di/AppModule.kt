package com.jxareas.stonks.di

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.jxareas.stonks.data.local.database.StocksDatabase
import com.jxareas.stonks.data.remote.constants.ApiConstants
import com.jxareas.stonks.data.remote.service.StocksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockService(): StocksService =
        Retrofit
            .Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun providesStockDatabase(@ApplicationContext context: Context): StocksDatabase =
        Room
            .databaseBuilder(context, StocksDatabase::class.java, StocksDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


}