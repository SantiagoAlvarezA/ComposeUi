package com.composeui.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.composeui.R
import com.composeui.domain.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToTask: (Task) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val state = viewModel.state
    //val context = LocalContext.current

    val (showDialog, onDismissRequest) = remember { mutableStateOf(false) }

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    var saveEnable by remember { mutableStateOf(false) }
    saveEnable = title.isNotEmpty() && description.isNotEmpty()


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(),
                title = {
                    Text(text = stringResource(R.string.app_name))
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onDismissRequest(!showDialog)
            }) {
                Icon(
                    imageVector = Icons.Filled.Add, contentDescription = "Localized description"
                )
            }
        },
        content = { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {

                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize()
                    )
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
                            navigateToTask(it)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(72.dp))
                    }
                }

                DialogFullScreen(
                    showDialog = showDialog,
                    onDismissRequest = {
                        onDismissRequest(it)
                        title = ""
                        description = ""
                    },
                    topBar = {

                        TopAppBar(
                            navigationIcon = {
                                IconButton(onClick = {
                                    onDismissRequest(false)
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Close"
                                    )
                                }
                            },
                            title = {
                                Text(text = stringResource(R.string.create_task))
                            },
                            actions = {
                                if (saveEnable)
                                    IconButton(
                                        onClick = {
                                            viewModel.save(title = title, description = description)
                                            onDismissRequest(false)
                                        },
                                    ) {
                                        Text(text = "Save")
                                    }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                if (saveEnable) {
                                    viewModel.save(title = title, description = description)
                                    onDismissRequest(false)
                                }
                            },
                            containerColor = if (saveEnable) MaterialTheme.colorScheme.primaryContainer else Gray,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done"
                            )
                        }
                    },
                    content = {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            val modifier = Modifier.fillMaxWidth()
                            OutlinedTextField(
                                modifier = modifier,
                                value = title,
                                onValueChange = { title = it },
                                label = { Text("tittle") },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            OutlinedTextField(
                                modifier = modifier,
                                value = description,
                                onValueChange = { description = it },
                                label = { Text("Description") },
                            )
                        }
                    }
                )
            }
        })


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogFullScreen(
    showDialog: Boolean,
    onDismissRequest: (Boolean) -> Unit,
    topBar: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                onDismissRequest(false)
            }) {
            Scaffold(
                topBar = {
                    topBar()
                },
                floatingActionButton = {
                    floatingActionButton()
                },
                content = { paddings ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddings)
                            .padding(horizontal = 12.dp)
                    ) {
                        content()
                    }
                })
        }
    } else {
        onDismissRequest(false)
    }

}