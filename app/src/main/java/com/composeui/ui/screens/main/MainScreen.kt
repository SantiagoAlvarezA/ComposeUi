package com.composeui.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.composeui.domain.model.Product
import com.composeui.ui.navigation.Screen

@Composable
fun MainScreen(
    navControlLer: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val products: List<Product> by viewModel.products.observeAsState(emptyList())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(products) { product ->
                MiCompose(product, navControlLer)
            }

        }


    }
}


@Composable
fun MiCompose(product: Product, navControlLer: NavController) {
    Card(modifier = Modifier
        .padding(12.dp)
        .clickable {
            navControlLer.navigate(Screen.ProductScreen.withArgs("${product.id}"))
        }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = product.title)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.description)
        }

    }
}