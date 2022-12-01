package com.composeui.data.server.apisource

import com.composeui.data.Resource
import com.composeui.data.mappers.toDomainProduct
import com.composeui.data.server.BaseApiCall
import com.composeui.data.server.api.Api
import com.composeui.data.server.model.BaseResponse
import com.composeui.data.server.model.ProductResponse
import com.composeui.data.source.remote.RemoteDataSource
import com.composeui.domain.model.Product
import com.google.gson.Gson
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteDataSource, BaseApiCall() {

    override suspend fun getRemoteProducts(): List<Product> =
        when (val response = safeApiCall { api.getAllProducts() }) {
            is Resource.Success -> {
                response.data.products.map { it.toDomainProduct() }
            }
            else -> emptyList()
        }
}