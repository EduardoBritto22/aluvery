package com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.aluvery.model.Product
import com.alura.aluvery.sampledata.listOfProducts
import com.alura.aluvery.ui.components.ProductItem
import com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun AllProductsScreen(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            Text(
                "All products",
                Modifier.padding(
                    end = 16.dp
                ),
                fontSize = 25.sp,
                fontWeight = FontWeight(400)
            )
        }
        items(products) { p ->
            ProductItem(p)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AllProductsScreenPreview() {
    AluveryTheme {
        Surface {
            AllProductsScreen(listOfProducts)
        }
    }
}
