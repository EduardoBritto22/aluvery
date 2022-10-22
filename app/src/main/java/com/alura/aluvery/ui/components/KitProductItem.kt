package com.alura.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.aluvery.ui.theme.Purple200
import com.alura.aluvery.ui.theme.Purple700
import com.alura.aluvery.R


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




@Preview(showBackground = true)
@Composable
private fun KitProductItemPreview() {
    KitProductItem()
}


