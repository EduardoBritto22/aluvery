package com.alura.aluvery

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.aluvery.ui.theme.AluveryTheme

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
fun ProductItem(){
    Column {
        Box( Modifier
            .height(50.dp)
            .background(Color.Blue)
            .width(50.dp)
        )
        Image(painter =  painterResource(R.drawable.ic_launcher_background), contentDescription = "Picture of the product")
        Text("Texto 1")
        Text("Texto 2")
    }
}
