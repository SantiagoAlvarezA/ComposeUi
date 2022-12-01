package com.composeui.data.source.remote

import com.composeui.domain.model.Product

interface RemoteDataSource {

    suspend fun getRemoteProducts(): List<Product>
}