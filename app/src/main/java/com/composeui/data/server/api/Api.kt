package com.composeui.data.server.api

import com.composeui.data.server.model.BaseResponse
import com.composeui.data.server.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductResponse>
}