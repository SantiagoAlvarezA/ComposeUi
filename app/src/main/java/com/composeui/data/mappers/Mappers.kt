package com.composeui.data.mappers

import com.composeui.data.database.model.Product as DbProduct
import com.composeui.data.server.model.Product as ServerProduct
import com.composeui.domain.model.Product as DomainProduct

fun DomainProduct.toDbProduct(): DbProduct =
    DbProduct(
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

fun List<DomainProduct>.toDbProducts(): List<DbProduct> =
    map { it.toDbProduct() }


fun DbProduct.toDomainProduct(): DomainProduct = DomainProduct(
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

fun List<DbProduct>.toDomainProducts(): List<DomainProduct> =
    map{it.toDomainProduct()}

fun ServerProduct.toDomainProduct(): DomainProduct =
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


@JvmName("toDomainProductsServerProduct")
fun List<ServerProduct>.toDomainProducts(): List<DomainProduct> =
    map{it.toDomainProduct()}
