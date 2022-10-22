package com.alura.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.aluvery.extensions.toBrazilianCurrency
import com.alura.aluvery.model.Product
import com.alura.aluvery.ui.theme.*
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    KitProductItem()
                }
            }
        }
    }
}

@Composable
fun ProductSection() {
    Column {
        Text(
            "Promotions",
            Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            Modifier.padding(
                top = 8.dp,
                bottom = 16.dp
            )
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier)
            ProductItem(
                Product(
                    name = "Hamburguer",
                    price = BigDecimal("12.99"),
                    image = R.drawable.burger
                )
            )
            ProductItem(
                Product(
                    name = "French Fries",
                    price = BigDecimal("7.99"),
                    image = R.drawable.fries
                )
            )
            ProductItem(
                Product(
                    name = "Pizza",
                    price = BigDecimal("19.99"),
                    image = R.drawable.pizza
                )
            )
            Spacer(Modifier)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp
    ) {
        Column(
            Modifier.heightIn(min = 250.dp, 300.dp)
                .width(200.dp)
        ) {
            val imageSize = 100.dp
            Box(
                Modifier
                    .height(imageSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Purple500,
                                Teal200
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(product.image),
                    contentDescription = "Picture of the product",
                    Modifier.size(imageSize)
                        .offset(y = imageSize / 2) // Half of the height
                        .clip(CircleShape)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(imageSize / 2))
            Column(
                Modifier.padding(16.dp),
            ) {
                Text(
                    product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    product.price.toBrazilianCurrency(),
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }

        }
    }
}


@Composable
fun KitProductItem() {
    Surface(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Row(
            Modifier.height(200.dp)
                .widthIn(300.dp, 350.dp)
        ) {
            Box(
                Modifier.fillMaxHeight()
                    .width(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Purple700,
                                Purple200
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Picture of the product kit",
                    Modifier.size(100.dp)
                        .offset(50.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Purple200,
                                    Purple700
                                )
                            ),
                            shape = CircleShape
                        )
                )
            }
            Spacer(Modifier.width(50.dp))
            Text(
                LoremIpsum(20).values.first(),
                Modifier.padding(20.dp)
                    .align(Alignment.CenterVertically),
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )

        }
    }
}

/********************** Previews ***************/

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        Product(
            name = LoremIpsum(50).values.first(),
            price = BigDecimal("14.99"),
            image = R.drawable.ic_launcher_background
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun KitProductItemPreview() {
    KitProductItem()
}

@Preview(showBackground = true)
@Composable
private fun ProductSectionPreview() {
    ProductSection()
}