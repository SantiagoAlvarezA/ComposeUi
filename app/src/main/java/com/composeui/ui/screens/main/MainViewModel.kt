package com.composeui.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeui.domain.model.Task
import com.composeui.domain.resource.Resource
import com.composeui.usecases.model.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {


    var state by mutableStateOf(MainScreenState())

    init {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            useCase.getTasks().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        state = state.copy(
                            tasks = it.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = true,
                            error = "Error",
                            tasks = emptyList()
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            tasks = emptyList(),
                            isLoading = true,
                            error = null
                        )
                    }
                    else -> Unit
                }

            }

        }
    }

    fun save(title: String, description: String) = viewModelScope.launch {
        useCase.saveTask(Task(id = 0, title = title, description = description))
    }
}