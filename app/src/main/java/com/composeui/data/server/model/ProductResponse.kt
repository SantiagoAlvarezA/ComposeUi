package com.composeui.data.server.model

data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
)