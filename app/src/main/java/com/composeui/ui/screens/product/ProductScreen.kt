package com.composeui.ui.screens.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.composeui.domain.model.Product

@Composable
fun ProductScreen(
    navControlLer: NavController,
    id: Int,
    viewModel: ProductViewModel = hiltViewModel()
) {
    viewModel.getProduct(id)
    val product: Product? by viewModel.product.observeAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Product: ${product?.description}",
            modifier = Modifier.clickable { navControlLer.popBackStack() })
    }
}