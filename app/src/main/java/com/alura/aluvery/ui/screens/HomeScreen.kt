package com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.aluvery.model.Product
import com.alura.aluvery.sampledata.listOfProducts
import com.alura.aluvery.sampledata.sampleSections
import com.alura.aluvery.ui.components.CardProductItem
import com.alura.aluvery.ui.components.ProductSection
import com.alura.aluvery.ui.components.SearchTextField
import com.alura.aluvery.ui.theme.AluveryTheme


class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    private val products: List<Product> = emptyList(),
    searchText: String = ""
) {
    var text by mutableStateOf(searchText)
        private set

    val searchedProducts
        get() =
            if (text.isNotBlank()) {
                listOfProducts.filter(containsInNameOrDescription()) +
                        products.filter(containsInNameOrDescription())
            } else {
                emptyList()
            }

    private fun containsInNameOrDescription() = { p: Product ->
        p.description?.contains(text.trim(), ignoreCase = true) == true
                || p.name.contains(text.trim(), ignoreCase = true)
    }

    fun isShownSections() = text.isBlank()

    val onSearchChange: (String) -> Unit = { searchText ->
        text = searchText
    }
}


@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState(),
) {
    Column {
        val sections = state.sections
        val text = state.text
        val searchedProducts = remember(text) { state.searchedProducts }

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
