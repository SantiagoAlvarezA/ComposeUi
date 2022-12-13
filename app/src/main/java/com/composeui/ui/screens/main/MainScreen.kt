package com.composeui.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import kotlin.math.round

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProduct: (Product) -> Unit,
) {
    val state = viewModel.state

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if(state.isLoading){
            CircularProgressIndicator()
        }

        state.error?.let {
            Text(text = it)
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(state.products) { product ->
                ProductCompose(product) {
                    navigateToProduct(it)
                }
            }

        }



    }
}


@Composable
fun ProductCompose(product: Product, onClickItem: (Product) -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp, 6.dp)
        .clickable {
            onClickItem(product)
        },

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