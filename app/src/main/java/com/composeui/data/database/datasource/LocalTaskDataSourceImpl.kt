package com.composeui.data.database.datasource

import com.composeui.data.database.dao.TaskDao
import com.composeui.data.mappers.toDbTask
import com.composeui.data.mappers.toDomainTask
import com.composeui.data.mappers.toDomainTasks
import com.composeui.data.source.local.LocalTaskDataSource
import com.composeui.domain.model.Task
import com.composeui.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalTaskDataSourceImpl @Inject constructor(
    private val taskDao: TaskDao
) : LocalTaskDataSource {

    override suspend fun saveTask(task: Task) =
        taskDao.insert(task.toDbTask())

    override fun getTasks(): Flow<Resource<List<Task>>> =
        flow {
            emit(Resource.Loading)
            val tasks = taskDao.get()
            tasks.collect {
                emit(Resource.Success(it.toDomainTasks()))
            }
        }

    override fun findTaskById(id: Int): Flow<Resource<Task>> = flow {
        emit(Resource.Loading)
        taskDao.findById(id)?.let {
            emit(Resource.Success(it.toDomainTask()))
        } ?: run {
            emit(Resource.Error())
        }
    }
}


