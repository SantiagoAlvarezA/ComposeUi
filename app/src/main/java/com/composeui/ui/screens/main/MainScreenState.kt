package com.composeui.ui.screens.main

import com.composeui.domain.model.Task

data class MainScreenState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
