package com.composeui.di.module

import com.composeui.data.database.dao.TaskDao
import com.composeui.data.database.datasource.LocalTaskDataSourceImpl
import com.composeui.data.server.api.Api
import com.composeui.data.server.apisource.RemoteTaskDataSourceImpl
import com.composeui.data.source.local.LocalTaskDataSource
import com.composeui.data.source.remote.RemoteTaskDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideLocalTaskDataSource(taskDao: TaskDao): LocalTaskDataSource =
        LocalTaskDataSourceImpl(taskDao)

    @Provides
    fun provideRemoteTaskDataSourceImpl(api: Api): RemoteTaskDataSource =
        RemoteTaskDataSourceImpl(api)
}