package com.composeui.usecases

import com.composeui.data.repository.Repository
import com.composeui.domain.model.Product
import kotlinx.coroutines.flow.Flow


class UseCase(
    private val repository: Repository
) {
    suspend fun getRemoteProducts() =
        repository.getRemoteProducts()

    fun getProducts(): Flow<List<Product>> =
        repository.getProducts()

    fun getProduct(id: Int): Flow<Product> =
        repository.getProduct(id)
}