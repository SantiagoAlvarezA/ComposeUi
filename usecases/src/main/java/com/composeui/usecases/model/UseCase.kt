package com.composeui.usecases.model

import com.composeui.data.repository.TaskRepository
import com.composeui.domain.model.Task
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow


class UseCase(
    private val repository: TaskRepository
) {
    suspend fun saveTask(task: Task) =
        repository.saveTask(task)

    fun getTasks(): Flow<Resource<List<Task>>> =
        repository.getTasks()

    fun findTaskById(id: Int): Flow<Resource<Task>> =
        repository.findTaskById(id)
}