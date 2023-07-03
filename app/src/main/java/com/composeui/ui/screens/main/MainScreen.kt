package com.composeui.ui.screens.main

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composeui.R
import com.composeui.domain.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProduct: (Task) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val state = viewModel.state
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(),
                title = {
                    Text(text = stringResource(R.string.app_name))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(context, "hello", Toast.LENGTH_LONG).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Localized description"
                )
            }
        },
        content = { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {

                if (state.isLoading) {
                    CircularProgressIndicator()
                }

                state.error?.let {
                    Text(text = it)
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(180.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(state.tasks) { product ->
                        TaskCompose(product) {
                            navigateToProduct(it)
                        }
                    }
                }
            }
        }
    )


}


@Composable
fun TaskCompose(task: Task, onClickItem: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 6.dp)
            .clickable {
                onClickItem(task)
            },

        ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = task.title)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = task.description)
        }

    }
}

@Composable
fun Dialog(onDismiss: Boolean) {

    val openDialog = remember { mutableStateOf(onDismiss) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Title")
            },
            text = {
                Text(
                    "This area typically contains the supportive text " +
                            "which presents the details regarding the Dialog's purpose."
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }

}