package com.composeui.ui.screens.main

import com.composeui.domain.model.Product

data class MainScreenState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
