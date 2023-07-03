package com.composeui.ui.screens.task

import com.composeui.domain.model.Task

data class TaskScreenState(
    val task: Task? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)