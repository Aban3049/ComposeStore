package com.abanapps.composestore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanapps.models.Product
import com.abanapps.useCase.GetFeaturedProductUseCase
import com.abanapps.useCase.GetPopularProductUseCase
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFeaturedProductUseCase: GetFeaturedProductUseCase,
    private val getPopularProductUseCase: GetPopularProductUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<FeatureProductsUiEvents>(FeatureProductsUiEvents.Loading)
    val uiStates = _uiState.asStateFlow()

    private val _uiStatePopularProduct =
        MutableStateFlow<PopularProductsUiEvent>(PopularProductsUiEvent.Loading)
    val uiStatePopularProduct = _uiStatePopularProduct.asStateFlow()

    init {
        getFeaturedProducts()
        getPopularProducts()
    }

    private fun getFeaturedProducts() {

        _uiState.value = FeatureProductsUiEvents.Loading

        viewModelScope.launch {
            getFeaturedProductUseCase.invoke().onError {
                _uiState.value = FeatureProductsUiEvents.Error(it.name)
            }.onSuccess {
                _uiState.value = FeatureProductsUiEvents.Success(it)
            }
        }

    }

    private fun getPopularProducts() {

        _uiStatePopularProduct.value = PopularProductsUiEvent.Loading

        viewModelScope.launch {
            getPopularProductUseCase.invoke().onError {
                _uiStatePopularProduct.value = PopularProductsUiEvent.Error(it.name)
            }.onSuccess {
                _uiStatePopularProduct.value = PopularProductsUiEvent.Success(it)
            }
        }
    }


    sealed class FeatureProductsUiEvents {
        data object Loading : FeatureProductsUiEvents()
        data object NavigateToProductDetail : FeatureProductsUiEvents()
        data class Success(val products: List<Product>) : FeatureProductsUiEvents()
        data class Error(val message: String) : FeatureProductsUiEvents()
    }

    sealed class PopularProductsUiEvent {
        data object Loading : PopularProductsUiEvent()
        data object NavigateToProductDetail : PopularProductsUiEvent()
        data class Success(val product: List<Product>) : PopularProductsUiEvent()
        data class Error(val message: String) : PopularProductsUiEvent()
    }

}