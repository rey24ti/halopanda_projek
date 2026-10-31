package com.example.rey_halopanda.projek

object CartManager {
    // List untuk menyimpan barang di keranjang
    val daftarKeranjang = mutableListOf<CartItem>()

    // Fungsi Tambah Barang
    fun tambahKeKeranjang(produk: ProdukModel) {
        val itemAda = daftarKeranjang.find { it.produk.nama == produk.nama }
        if (itemAda != null) {
            itemAda.jumlah++ // Jika sudah ada, tambah qty
        } else {
            daftarKeranjang.add(CartItem(produk, 1)) // Jika belum, masukkan baru
        }
    }

    // Fungsi Hitung Total Harga
    fun hitungTotal(): Int {
        var total = 0
        for (item in daftarKeranjang) {
            // Mengubah string "Rp 58.000" menjadi int 58000
            val hargaBersih = item.produk.harga.replace("Rp ", "").replace(".", "").trim().toIntOrNull() ?: 0
            total += (hargaBersih * item.jumlah)
        }
        return total
    }
}