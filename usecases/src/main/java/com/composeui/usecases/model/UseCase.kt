package com.composeui.usecases.model

import com.composeui.data.repository.Repository
import com.composeui.domain.model.Product
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow


class UseCase(
    private val repository: Repository
) {
    suspend fun getRemoteProducts(): Resource<Boolean> =
        repository.getRemoteProducts()

    fun getProducts(): Flow<Resource<List<Product>>> =
        repository.getProducts()

    fun getProduct(id: Int): Flow<Resource<Product>> =
        repository.getProduct(id)
}