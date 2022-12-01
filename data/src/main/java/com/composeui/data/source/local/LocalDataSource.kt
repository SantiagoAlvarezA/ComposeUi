package com.composeui.data.source.local

import com.composeui.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveProducts(products: List<Product>)

    fun getProducts(): Flow<List<Product>>

    fun getProduct(id: Int): Flow<Product>
}