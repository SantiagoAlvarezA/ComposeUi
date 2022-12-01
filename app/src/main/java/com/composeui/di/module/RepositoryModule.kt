package com.composeui.di.module

import com.composeui.data.repository.Repository
import com.composeui.data.source.local.LocalDataSource
import com.composeui.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun productRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ) = Repository(
        localDataSource,
        remoteDataSource
    )
}