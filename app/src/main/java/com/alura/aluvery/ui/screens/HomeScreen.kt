package com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.aluvery.model.Product
import com.alura.aluvery.sampledata.listOfProducts
import com.alura.aluvery.sampledata.sampleCandies
import com.alura.aluvery.sampledata.sampleDrinks
import com.alura.aluvery.sampledata.sampleSections
import com.alura.aluvery.ui.components.CardProductItem
import com.alura.aluvery.ui.components.ProductSection
import com.alura.aluvery.ui.components.SearchTextField
import com.alura.aluvery.ui.theme.AluveryTheme


class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShownSections() = searchText.isBlank()
}

@Composable
fun HomeScreen(
    products: List<Product>
) {

    val sections = mapOf(
        "All products" to products,
        "Promotions" to sampleDrinks + sampleCandies,
        "Drinks" to sampleDrinks,
        "Candies" to sampleCandies
    )

    var text by rememberSaveable {
        mutableStateOf("")
    }


    fun containsInNameOrDescription() = { p: Product ->
        p.description?.contains(text.trim(), ignoreCase = true) == true
                || p.name.contains(text.trim(), ignoreCase = true)
    }
    val searchedProducts = remember(text,products) {
        if (text.isNotBlank()) {
            listOfProducts.filter(containsInNameOrDescription()) +
                    products.filter(containsInNameOrDescription())
        } else {
            emptyList()
        }
    }

    val state = remember(products, text) {
        HomeScreenUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }

    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState(),
) {
    Column {
        val sections = state.sections
        val text = state.searchText
        val searchedProducts =  state.searchedProducts

        SearchTextField(
            searchText = text,
            onSearchTextChange = state.onSearchChange
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
        ) {
            if (state.isShownSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState(sections = sampleSections))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewWithSearchText() {
    AluveryTheme {
        Surface {
            HomeScreen(
                state = HomeScreenUiState(
                    sections = sampleSections,
                    searchText = "a"
                ),
            )
        }
    }
}
