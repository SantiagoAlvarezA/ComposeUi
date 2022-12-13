package com.composeui.data.server.apisource

import com.composeui.data.Resource
import com.composeui.data.mappers.toDomainProduct
import com.composeui.data.server.BaseApiCall
import com.composeui.data.server.api.Api
import com.composeui.data.source.remote.RemoteDataSource
import com.composeui.domain.model.Product
import javax.inject.Inject
import com.composeui.domain.resource.Resource as DomainResource

class RemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteDataSource, BaseApiCall() {

    override suspend fun getRemoteProducts(): DomainResource<List<Product>> =
        when (val response = safeApiCall { api.getAllProducts() }) {
            is Resource.Success -> {
                DomainResource.Success(response.data.products.map { it.toDomainProduct() })
            }
            else -> DomainResource.Error()
        }
}