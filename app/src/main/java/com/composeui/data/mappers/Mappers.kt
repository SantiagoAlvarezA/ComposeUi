package com.composeui.data.mappers

import com.composeui.domain.model.Product
import com.composeui.data.database.model.Product as DomainProduct
import com.composeui.data.server.model.Product as ServerProduct

fun Product.toRoomProduct(): DomainProduct =
    DomainProduct(
        id,
        title,
        description,
        price,
        discountPercentage,
        rating,
        stock,
        brand,
        category,
        thumbnail
    )

fun DomainProduct.toDomainProduct(): Product = Product(
    id,
    title,
    description,
    price,
    discountPercentage,
    rating,
    stock,
    brand,
    category,
    thumbnail
)

fun ServerProduct.toDomainProduct(): Product =
    Product(
        id,
        title,
        description,
        price,
        discountPercentage,
        rating,
        stock,
        brand,
        category,
        thumbnail
    )