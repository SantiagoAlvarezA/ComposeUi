package com.composeui.data.repository

import com.composeui.data.source.local.LocalDataSource
import com.composeui.data.source.remote.RemoteDataSource
import com.composeui.domain.model.Product
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getRemoteProducts(): Resource<Boolean> =
        when (val result = remoteDataSource.getRemoteProducts()) {
            is Resource.Success -> {
                localDataSource.saveProducts(result.data)
                Resource.Success(true)
            }
            else -> {
                Resource.Error()
            }
        }


    fun getProducts(): Flow<Resource<List<Product>>> =
        localDataSource.getProducts()

    fun getProduct(id: Int): Flow<Resource<Product>> =
        localDataSource.getProduct(id)
}