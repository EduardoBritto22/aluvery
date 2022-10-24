package com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alura.aluvery.R
import com.alura.aluvery.extensions.toBrazilianCurrency
import com.alura.aluvery.model.Product
import com.alura.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

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
                                                    MaterialTheme.colors.primary,
                                                    MaterialTheme.colors.secondary
                                            )
                                    )
                            )
                            .fillMaxWidth()
            ) {
                AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Picture of the product",
                        Modifier.size(imageSize)
                                .offset(y = imageSize / 2) // Half of the height
                                .clip(CircleShape)
                                .align(Alignment.BottomCenter),
                        contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.placeholder)
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

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    AluveryTheme {
        Surface {
            ProductItem(
                    Product(
                            name = LoremIpsum(50).values.first(),
                            price = BigDecimal("14.99")
                    )
            )
        }
    }
}