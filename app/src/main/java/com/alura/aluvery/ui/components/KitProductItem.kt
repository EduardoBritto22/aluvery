package com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest


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
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.secondary
                            )
                        )
                    )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://images.pexels.com/photos/1352278/pexels-photo-1352278.jpeg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Picture of the product kit",
                    Modifier.size(100.dp)
                        .offset(50.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colors.primary,
                                    MaterialTheme.colors.secondary
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


@Preview(showBackground = true)
@Composable
private fun KitProductItemPreview() {
    KitProductItem()
}


