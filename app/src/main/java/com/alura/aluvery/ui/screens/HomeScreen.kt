package com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.aluvery.sampledata.sampleSections
import com.alura.aluvery.ui.components.CardProductItem
import com.alura.aluvery.ui.components.ProductSection
import com.alura.aluvery.ui.components.SearchTextField
import com.alura.aluvery.ui.states.HomeScreenUiState
import com.alura.aluvery.ui.theme.AluveryTheme
import com.alura.aluvery.ui.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
) {

    val state = viewModel.uiState

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
