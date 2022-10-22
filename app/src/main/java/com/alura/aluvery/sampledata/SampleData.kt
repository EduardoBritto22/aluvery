package com.alura.aluvery.sampledata

import com.alura.aluvery.R
import com.alura.aluvery.model.Product
import java.math.BigDecimal

val listOfProducts = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("12.99"),
        image = R.drawable.burger
    ),

    Product(
        name = "French Fries",
        price = BigDecimal("7.99"),
        image = R.drawable.fries
    ),

    Product(
        name = "Pizza",
        price = BigDecimal("19.99"),
        image = R.drawable.pizza
    )
)
