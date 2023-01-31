package com.composeui.data.server.model

data class BaseResponse(
    val status: Int,
    val message: String,
    val data: Any
)