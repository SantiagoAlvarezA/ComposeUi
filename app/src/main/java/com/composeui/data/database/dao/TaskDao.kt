package com.composeui.data.database.dao

import androidx.room.*
import com.composeui.data.database.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun get(): Flow<List<Task>>

    @Query(
        """
        SELECT 
            * 
        FROM Task 
        WHERE id = :id
        """
    )
    fun findById(id: Int): Task?

    @Query("SELECT COUNT(id) FROM Task")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(items: List<Task>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(items: Task)

    @Update
    fun update(product: Task)
}