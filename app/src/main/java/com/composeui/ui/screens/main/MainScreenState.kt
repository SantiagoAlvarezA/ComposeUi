package com.composeui.ui.screens.main

import com.composeui.domain.model.Task

data class MainScreenState(
    val products: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
