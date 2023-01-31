package com.composeui.ui.screens.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.composeui.ui.screens.main.TaskCompose

@Composable
fun ProductScreen(
    id: Int,
    viewModel: TaskViewModel = hiltViewModel(),
    toBack: () -> Unit,
) {
    val state = viewModel.state

    viewModel.findTaskById(id)

    if (state.isLoading) {
        CircularProgressIndicator()
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        state.task?.let {
            TaskCompose(it) {
                toBack()
            }
        }
    }


}