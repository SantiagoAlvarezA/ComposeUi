package com.composeui.di.module

import com.composeui.data.repository.TaskRepository
import com.composeui.data.source.local.LocalTaskDataSource
import com.composeui.data.source.remote.RemoteTaskDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun taskRepositoryProvider(
        localTaskDataSource: LocalTaskDataSource,
        remoteTaskDataSource: RemoteTaskDataSource,
    ) = TaskRepository(
        localTaskDataSource,
        remoteTaskDataSource
    )
}