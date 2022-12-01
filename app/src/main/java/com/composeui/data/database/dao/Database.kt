package com.composeui.data.database.dao

import androidx.room.RoomDatabase
import com.composeui.data.database.model.Product

import androidx.room.Database

@Database(
    entities = [
        Product::class,
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class Database : RoomDatabase() {

    abstract fun productDao(): ProductDao
}