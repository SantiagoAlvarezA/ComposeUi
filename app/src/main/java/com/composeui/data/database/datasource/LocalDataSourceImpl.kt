package com.composeui.data.database.datasource

import com.composeui.data.database.dao.ProductDao
import com.composeui.data.mappers.toDbProducts
import com.composeui.data.mappers.toDomainProduct
import com.composeui.data.mappers.toDomainProducts
import com.composeui.data.source.local.LocalDataSource
import com.composeui.domain.model.Product
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao
) : LocalDataSource {

    override suspend fun saveProducts(products: List<Product>) =
        productDao.insertProducts(products.toDbProducts())

    override fun getProducts(): Flow<Resource<List<Product>>> =
        flow {
            emit(Resource.Loading)
            val products = productDao.getAll()
            products.collect {
                it?.let {
                    emit(Resource.Success(it.toDomainProducts()))
                } ?: run {
                    emit(Resource.Error())
                }
            }
        }

    override fun getProduct(id: Int): Flow<Resource<Product>> = flow {
        emit(Resource.Loading)
        productDao.findById(id).collect {
            it?.let {
                emit(Resource.Success(it.toDomainProduct()))
            }?: run {
                emit(Resource.Error())
            }
        }
    }
}


