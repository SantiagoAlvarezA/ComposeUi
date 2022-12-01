package com.composeui.data.database.dao

import androidx.room.*
import com.composeui.data.database.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): Flow<List<Product>>

    @Query(
        """
        SELECT 
            * 
        FROM Product 
        WHERE id = :id
        """
    )
    fun findById(id: Int): Flow<Product>

    @Query("SELECT COUNT(id) FROM Product")
    fun itemsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProducts(items: List<Product>)

    @Update
    fun updateProduct(product: Product)
}