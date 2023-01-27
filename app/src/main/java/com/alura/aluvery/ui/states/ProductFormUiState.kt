package com.alura.aluvery.ui.states

data class ProductFormUiState(
    val description: String = "",
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val isPriceError: Boolean = false,
    val onPriceChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onUrlChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
) {
    fun urlIsNotBlank() = url.isNotBlank()
}