package com.composeui.data.server.apisource

import com.composeui.data.server.BaseApiCall
import com.composeui.data.server.api.Api
import com.composeui.data.source.remote.RemoteTaskDataSource
import javax.inject.Inject

class RemoteTaskDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteTaskDataSource, BaseApiCall() {

    /*override suspend fun getRemoteProducts(): DomainResource<List<Task>> =
        when (val response = safeApiCall { api.getAllProducts() }) {
            is Resource.Success -> {
                DomainResource.Success(response.data.products.map { it.toDomainProduct() })
            }
            else -> DomainResource.Error()
        }*/
}