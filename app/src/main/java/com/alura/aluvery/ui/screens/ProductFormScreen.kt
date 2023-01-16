package com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alura.aluvery.R
import com.alura.aluvery.model.Product
import com.alura.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal


class ProductFormUiState(
    val description: String = "",
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val isPriceError: Boolean = false,
    val onPriceChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onUrlChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onValidateFormButtonClick: () -> Unit = {}
) {
    fun urlIsNotBlank() = url.isNotBlank()
}

@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {}
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }

    var url by rememberSaveable {
        mutableStateOf("")
    }

    var price by rememberSaveable {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }

    var isPriceError by rememberSaveable {
        mutableStateOf(false)
    }

    val state: ProductFormUiState = remember(
        name, url, description, price
    ) {
        ProductFormUiState(
            name = name,
            url = url,
            description = description,
            price = price,
            isPriceError = isPriceError,
            onNameChange = {
                name = it
            },
            onUrlChange = {
                url = it
            },
            onDescriptionChange = {
                description = it
            },
            onPriceChange = {
                isPriceError = try {
                    BigDecimal(it)
                    false
                } catch (e: IllegalArgumentException) {
                    it.isNotEmpty()
                }
                price = it
            },
            onValidateFormButtonClick = {
                val convertedPrice = try {
                    BigDecimal(price)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = name,
                    image = url,
                    price = convertedPrice,
                    description = description
                )
                onSaveClick(product)
            }
        )
    }
    ProductFormScreen(state = state)
}

@Composable
fun ProductFormScreen(
    state: ProductFormUiState = ProductFormUiState()
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        Text(
            text = stringResource(R.string.product_form_title),
            Modifier.fillMaxWidth(),
            fontSize = 28.sp
        )


        if (state.urlIsNotBlank()) {
            AsyncImage(
                model = state.url,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .heightIn(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }
        TextField(
            value = state.url,
            onValueChange = state.onUrlChange,
            Modifier.fillMaxWidth(),
            label = {
                Text("Url of the image")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )


        TextField(
            value = state.name,
            onValueChange = state.onNameChange,
            Modifier.fillMaxWidth(),
            label = {
                Text("Name")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        Column {
            TextField(
                value = state.price,
                onValueChange = state.onPriceChange,
                Modifier.fillMaxWidth(),
                isError = state.isPriceError,
                label = {
                    Text("Price")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                )
            )
            if (state.isPriceError) {
                Text(
                    text = "The price must be a decimal value",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        TextField(
            value = state.description,
            onValueChange = state.onDescriptionChange,
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text("Description")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Button(
            onClick = state.onValidateFormButtonClick,
            Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier)
    }
}

@Composable
@Preview
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(onSaveClick = {})
        }
    }
}