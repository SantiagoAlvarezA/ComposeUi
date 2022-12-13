package com.composeui.data.source.local


import com.composeui.domain.model.Product
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveProducts(products: List<Product>)

    fun getProducts(): Flow<Resource<List<Product>>>

    fun getProduct(id: Int): Flow<Resource<Product>>
}