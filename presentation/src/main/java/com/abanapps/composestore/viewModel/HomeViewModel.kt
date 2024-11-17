package com.abanapps.composestore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanapps.models.Product
import com.abanapps.useCase.GetProductUseCase
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUiEvents>(HomeScreenUiEvents.Loading)
    val uiStates = _uiState.asStateFlow()

    init {
        getProducts()
    }


    private fun getProducts() {

        _uiState.value = HomeScreenUiEvents.Loading

        viewModelScope.launch {
            getProductUseCase.invoke().onError {
                _uiState.value = HomeScreenUiEvents.Error(it.name)
            }.onSuccess {
                _uiState.value = HomeScreenUiEvents.Success(it)
            }
        }

    }

    sealed class HomeScreenUiEvents {
        data object Loading : HomeScreenUiEvents()
        data object NavigateToProductDetail : HomeScreenUiEvents()
        data class Success(val products: List<Product>) : HomeScreenUiEvents()
        data class Error(val message: String) : HomeScreenUiEvents()
    }
}