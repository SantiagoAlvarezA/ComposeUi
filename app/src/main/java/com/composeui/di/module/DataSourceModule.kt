package com.composeui.di.module

import com.composeui.data.database.dao.ProductDao
import com.composeui.data.database.datasource.LocalDataSourceImpl
import com.composeui.data.server.api.Api
import com.composeui.data.server.apisource.RemoteDataSourceImpl
import com.composeui.data.source.local.LocalDataSource
import com.composeui.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideCategoryDataSource(productDao: ProductDao): LocalDataSource =
        LocalDataSourceImpl(productDao)

    @Provides
    fun provideRemoteDataSourceImpl(api: Api): RemoteDataSource =
        RemoteDataSourceImpl(api)
}