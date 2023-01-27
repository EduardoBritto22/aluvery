package com.alura.aluvery.dao

import com.alura.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {

    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

    fun products(): StateFlow<List<Product>> = products.asStateFlow() // Important to add the 'asStateFlow' to pass a copy that cannot be changed

    fun save(product: Product) {
        products.value = products.value + product
    }

}