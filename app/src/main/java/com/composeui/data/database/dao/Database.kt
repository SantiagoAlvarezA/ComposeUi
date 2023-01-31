package com.composeui.data.database.dao

import androidx.room.RoomDatabase
import com.composeui.data.database.model.Task

import androidx.room.Database

@Database(
    entities = [
        Task::class,
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class Database : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}