package com.composeui.ui.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.composeui.domain.model.Product
import com.composeui.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private var _product = useCase.getProducts().asLiveData(Dispatchers.IO)
    val products: LiveData<List<Product>>
        get() = _product

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.getRemoteProducts()
            }
        }

    }
}