package com.alura.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.aluvery.ui.theme.AluveryTheme
import com.alura.aluvery.ui.theme.Purple500
import com.alura.aluvery.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductItem()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItem() {
    Column(
        Modifier.height(250.dp)
            .width(200.dp)
    ) {
        Box(
            Modifier
                .height(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Purple500,
                            Teal200
                        )
                    )
                )
                .fillMaxWidth()
        )
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Picture of the product",
            Modifier.size(100.dp)
                .offset(y = (-50).dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )
        Text("Texto 1")
        Text("Texto 2")
    }
}
