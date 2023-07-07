package com.composeui.ui.screens.map

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MapScreen( viewModel: MapViewModel = hiltViewModel()){
    val state = viewModel.state



}