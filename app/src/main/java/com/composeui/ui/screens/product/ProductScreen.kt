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
import com.composeui.domain.model.Product
import com.composeui.ui.screens.main.ProductCompose

@Composable
fun ProductScreen(
    id: Int,
    viewModel: ProductViewModel = hiltViewModel(),
    toBack: () -> Unit,
) {
    viewModel.getProduct(id)
    val product: Product? by viewModel.product.observeAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        product?.let {
            ProductCompose(it) {
                toBack()
            }
        }
    }
}