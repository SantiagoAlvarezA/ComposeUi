package com.composeui.data.repository

import com.composeui.data.source.local.LocalDataSource
import com.composeui.data.source.remote.RemoteDataSource
import com.composeui.domain.model.Product
import kotlinx.coroutines.flow.Flow

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getRemoteProducts() {
        val products = remoteDataSource.getRemoteProducts()
        localDataSource.saveProducts(products)
    }

    fun getProducts(): Flow<List<Product>> =
        localDataSource.getProducts()

    fun getProduct(id: Int): Flow<Product> =
        localDataSource.getProduct(id)
}