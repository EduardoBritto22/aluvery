package com.alura.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alura.aluvery.dao.ProductDao
import com.alura.aluvery.model.Product
import com.alura.aluvery.sampledata.listOfProducts
import com.alura.aluvery.sampledata.sampleCandies
import com.alura.aluvery.sampledata.sampleDrinks
import com.alura.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()


    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(
        HomeScreenUiState()
    )

    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState->
            currentState.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        searchedProducts = searchedProducts(it)
                    )
                }
            )
        }

        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "All products" to products,
                        "Promotions" to sampleDrinks + sampleCandies,
                        "Drinks" to sampleDrinks,
                        "Candies" to sampleCandies
                    ),
                    searchedProducts = searchedProducts(_uiState.value.searchText)
                )
            }
        }
    }

    private fun containsInNameOrDescription(text: String) = { p: Product ->
        p.description?.contains(text.trim(), ignoreCase = true) == true
                || p.name.contains(text.trim(), ignoreCase = true)
    }

    private fun searchedProducts(text: String) =
        if (text.isNotBlank()) {
            listOfProducts.filter(containsInNameOrDescription(text)) +
                    dao.products().value.filter(containsInNameOrDescription(text))
        } else {
            emptyList()
        }

}