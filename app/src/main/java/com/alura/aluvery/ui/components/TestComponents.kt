package com.alura.aluvery.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Test implementations to study the composables
 */

@Preview(name = "Column", showBackground = true)
@Composable
fun ColumnPreview() {
    Column {
        Text("Texto 1")
        Text("Texto 2")
    }
}

@Preview(name = "Row", showBackground = true)
@Composable
fun RowPreview() {
    Row {
        Text("Texto 1")
        Text("Texto 2")
    }
}

@Preview(name = "Box", showBackground = true)
@Composable
fun BoxPreview() {
    Box {
        Text("Texto 1")
        Text("Texto 2")
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLayoutPreview() {
    Column(
        Modifier
            .background(color = Color.Blue)
            .padding(horizontal = 2.dp, vertical = 5.dp)
            .fillMaxWidth()
    ) {
        Text("Texto 1")
        Text("Texto 2")
        Row(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 16.dp
            ).background(Color.Green)
        ) {
            Text("Texto 3")
            Text("Texto 4")
        }
        Box(
            modifier = Modifier.padding(8.dp)
                .background(Color.Red)
                .padding(8.dp)
        ) {
            Row(
                Modifier.padding(8.dp)
                    .background(Color.Cyan)
                    .padding(8.dp)
            ) {
                Text("Texto 5")
                Text("Texto 6")
            }
            Column(
                Modifier.padding(8.dp)
                    .background(Color.Magenta)
                    .padding(8.dp)
            ) {
                Text("Texto 7")
                Text("Texto 8")
            }
        }
    }
}


@Composable
fun MyFirstComposable() {
    Text("My first Composable")
    Text("My second text bigger")
}

@Preview(
    name = "Teste de Preview",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyFirstComposablePreview() {
    MyFirstComposable()
}
