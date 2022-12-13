package com.composeui.data.source.remote

import com.composeui.domain.model.Product
import com.composeui.domain.resource.Resource

interface RemoteDataSource {

    suspend fun getRemoteProducts(): Resource<List<Product>>
}