package com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.aluvery.model.Product
import com.alura.aluvery.sampledata.listOfProducts
import com.alura.aluvery.sampledata.sampleSections
import com.alura.aluvery.ui.components.CardProductItem
import com.alura.aluvery.ui.components.ProductSection
import com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchText: String = ""
) {
    Column {

        var text by remember { mutableStateOf(searchText) }
        OutlinedTextField(
            value = text,
            onValueChange = { newValue ->
                text = newValue
            },
            Modifier.padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(100),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            },
            label = {
                Text("Product")
            },
            placeholder = {
                Text("What are you searching for?")
            }
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
        ) {
            if(text.isBlank()){
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
                items(listOfProducts){ p->
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
            HomeScreen(sampleSections)
        }
    }
}
@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewWithSearchText() {
    AluveryTheme {
        Surface {
            HomeScreen(
                sampleSections,
                searchText = "a",
            )
        }
    }
}
