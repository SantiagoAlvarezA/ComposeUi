package com.composeui.data.source.local


import com.composeui.domain.model.Task
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface LocalTaskDataSource {

    suspend fun saveTask(task: Task)

    fun getTasks(): Flow<Resource<List<Task>>>

    fun findTaskById(id: Int): Flow<Resource<Task>>
}