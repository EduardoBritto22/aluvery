package com.alura.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.alura.aluvery.dao.ProductDao
import com.alura.aluvery.ui.screens.ProductFormScreen
import com.alura.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {
    private val dao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(
                        onSaveClick = { product ->
                            dao.save(product)
                            finish()
                        }
                    )
                }
            }
        }
    }
}
