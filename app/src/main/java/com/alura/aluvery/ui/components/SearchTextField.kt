package com.alura.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun SearchTextField(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            onSearchTextChange(newValue)
        },
        modifier.padding(16.dp)
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


}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    AluveryTheme {
        Surface {
            SearchTextField(
                "",
                onSearchTextChange = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            SearchTextField(
                searchText = "a",
                onSearchTextChange = {},
            )
        }
    }
}