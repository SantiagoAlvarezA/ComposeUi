package com.composeui.data.mappers

import com.composeui.data.database.model.Task as DbTask
import com.composeui.data.server.model.Product as ServerTask
import com.composeui.domain.model.Task as DomainTask

fun DomainTask.toDbTask(): DbTask =
    DbTask(
        id,
        title,
        description
    )

fun List<DomainTask>.toDbTasks(): List<DbTask> =
    map { it.toDbTask() }


fun DbTask.toDomainTask(): DomainTask = DomainTask(
    id,
    title,
    description
)

fun List<DbTask>.toDomainTasks(): List<DomainTask> =
    map { it.toDomainTask() }

fun ServerTask.toDomainTask(): DomainTask =
    DomainTask(
        id,
        title,
        description
    )

@JvmName("toDomainTasksServerTask")
fun List<ServerTask>.toDomainTasks(): List<DomainTask> =
    map { it.toDomainTask() }
