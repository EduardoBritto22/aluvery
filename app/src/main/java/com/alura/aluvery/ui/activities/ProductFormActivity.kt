package com.alura.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alura.aluvery.R
import com.alura.aluvery.model.Product
import com.alura.aluvery.ui.theme.AluveryTheme
import java.lang.NumberFormatException
import java.math.BigDecimal

class ProductFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {

                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
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

        var url by remember {
            mutableStateOf("")
        }
        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
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
            value = url,
            onValueChange = {
                url = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text("Url of the image")
            }
        )

        var name by remember {
            mutableStateOf("")
        }
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text("Name")
            }
        )

        var price by remember {
            mutableStateOf("")
        }
        TextField(
            value = price,
            onValueChange = {
                price = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text("Price")
            }
        )

        var description by remember {
            mutableStateOf("")
        }
        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            Modifier.fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text("Description")
            }
        )

        Button(onClick = {

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
        }, Modifier.fillMaxWidth()) {
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
            ProductFormScreen()
        }
    }
}