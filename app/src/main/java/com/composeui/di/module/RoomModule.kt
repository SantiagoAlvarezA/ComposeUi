package com.composeui.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.composeui.data.database.dao.Database
import com.composeui.data.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): Database =
        Room.databaseBuilder(
            app.applicationContext,
            Database::class.java, "compose_ui.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                //TODO: implement callback onCreate
            })
            .build()

    @Provides
    @Singleton
    fun provideTaskDao(database: Database): TaskDao =
        database.taskDao()

}