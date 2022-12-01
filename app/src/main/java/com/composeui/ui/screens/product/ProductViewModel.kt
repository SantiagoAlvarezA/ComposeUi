package com.composeui.ui.screens.product


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeui.domain.model.Product
import com.composeui.usecases.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private var _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product


    fun getProduct(id: Int) = viewModelScope.launch {
        val product = withContext(Dispatchers.IO) {
            useCase.getProduct(id)
        }
        product.collectLatest {
            _product.value = it
        }
    }
}