package com.abanapps.composestore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanapps.models.Product
import com.abanapps.network.ResultWrapper
import com.abanapps.useCase.GetProductUseCase
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
        viewModelScope.launch {
            _uiState.value = HomeScreenUiEvents.Loading
            getProductUseCase.execute().let { result ->
                when (result) {
                    is ResultWrapper.Failure -> {
                        _uiState.value =
                            HomeScreenUiEvents.Error(result.exception.message ?: "Error")
                    }

                    is ResultWrapper.Success -> {
                        _uiState.value = HomeScreenUiEvents.Success(result.value)
                    }
                }
            }
        }
    }
}

sealed class HomeScreenUiEvents {

    data object Loading : HomeScreenUiEvents()
    data object NavigateToProductDetail : HomeScreenUiEvents()
    data class Success(val data: List<Product>) : HomeScreenUiEvents()
    data class Error(val message: String) : HomeScreenUiEvents()
}