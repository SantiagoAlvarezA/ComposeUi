package com.composeui.data.repository

import com.composeui.data.source.local.LocalTaskDataSource
import com.composeui.data.source.remote.RemoteTaskDataSource
import com.composeui.domain.model.Task
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val localTaskDataSource: LocalTaskDataSource,
    private val remoteTaskDataSource: RemoteTaskDataSource
) {

    suspend fun saveTask(task: Task) =
        localTaskDataSource.saveTask(task)

    fun getTasks(): Flow<Resource<List<Task>>> =
        localTaskDataSource.getTasks()

    fun findTaskById(id: Int): Flow<Resource<Task>> =
        localTaskDataSource.findTaskById(id)
}