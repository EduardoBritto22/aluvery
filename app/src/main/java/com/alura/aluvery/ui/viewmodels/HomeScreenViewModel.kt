package com.alura.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alura.aluvery.dao.ProductDao
import com.alura.aluvery.model.Product
import com.alura.aluvery.sampledata.listOfProducts
import com.alura.aluvery.sampledata.sampleCandies
import com.alura.aluvery.sampledata.sampleDrinks
import com.alura.aluvery.ui.states.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()


    var uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState(
        sections = mapOf(
            "All products" to dao.products(),
            "Promotions" to sampleDrinks + sampleCandies,
            "Drinks" to sampleDrinks,
            "Candies" to sampleCandies
        ),
        onSearchChange = {
            // Como o uiState é um MutableState, para que haja um evento de mudança, precisamos mudar a propria instancia do objeto.
            // Para fazer isso nos reatribuimos a referencia do uiState mas pegando uma copia do mesmo e modificando apenas o que foi modificado em onSearchChange
            uiState = uiState.copy(searchText = it, searchedProducts = searchedProducts(it))
        }
    ))
        private set


    private fun containsInNameOrDescription(text: String) = { p: Product ->
        p.description?.contains(text.trim(), ignoreCase = true) == true
                || p.name.contains(text.trim(), ignoreCase = true)
    }

    private fun searchedProducts(text: String) =
        if (text.isNotBlank()) {
            listOfProducts.filter(containsInNameOrDescription(text)) +
                    dao.products().filter(containsInNameOrDescription(text))
        } else {
            emptyList()
        }

}