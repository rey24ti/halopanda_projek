package com.example.rey_halopanda.projek
data class ProdukModel(
    val nama: String,
    val harga: String,
    val hargaCoret: String = "",
    val diskon: String = "",
    val gambarResId: Int,
    val rating: String = "5.0",
    val terjual: String = "100+",
    val isFlashSale: Boolean = false
)