package com.composeui.ui.screens.task


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeui.domain.resource.Resource
import com.composeui.usecases.model.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    var state by mutableStateOf(TaskScreenState())


    fun findTaskById(id: Int) = viewModelScope.launch {
        val product = withContext(Dispatchers.IO) {
            useCase.findTaskById(id)
        }
        product.collectLatest {
            Log.d("Error --> ","$it")

            when (it) {
                is Resource.Success -> {
                    state = state.copy(
                        task = it.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = "Error 1",
                        task = null
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true,
                        error = "Error 2",
                        task = null
                    )
                }

            }
        }
    }
}