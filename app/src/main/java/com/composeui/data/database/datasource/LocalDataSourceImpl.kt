package com.composeui.data.database.datasource

import com.composeui.data.database.dao.ProductDao
import com.composeui.data.mappers.toDomainProduct
import com.composeui.data.mappers.toRoomProduct
import com.composeui.data.source.local.LocalDataSource
import com.composeui.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao
) : LocalDataSource {

    override suspend fun saveProducts(products: List<Product>) =
        productDao.insertProducts(products.map { it.toRoomProduct() })


    override fun getProducts(): Flow<List<Product>> =
        flow {
            val products = productDao.getAll()
            products.collect {
                emit(it.map { _it -> _it.toDomainProduct() })
            }
        }

    override fun getProduct(id: Int): Flow<Product> = flow {
        productDao.findById(id).collect {
            emit(it.toDomainProduct())
        }
    }
}


