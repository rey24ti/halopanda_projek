package com.example.rey_halopanda.projek

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class beradaActivity : AppCompatActivity() {

    private lateinit var rvKategori: RecyclerView
    private lateinit var rvFlashSale: RecyclerView
    private lateinit var rvProduk: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_berada)

        // 1. Handling Edge-to-Edge (Safe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Init View
        rvKategori = findViewById(R.id.rvKategori)
        rvFlashSale = findViewById(R.id.rvFlashSale)
        rvProduk = findViewById(R.id.rvProduk)

        // 3. Setup Search Bar
        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(this, "Mencari: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            false
        }

        // 4. Setup Icon Cart & Notif
        findViewById<ImageView>(R.id.ivCart).setOnClickListener {
            Toast.makeText(this, "Buka Keranjang", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.ivNotif).setOnClickListener {
            Toast.makeText(this, "Buka Notifikasi", Toast.LENGTH_SHORT).show()
        }

        // 5. Setup RecyclerView Kategori
        setupKategori()

        // 6. Setup RecyclerView Flash Sale
        setupFlashSale()

        // 7. Setup RecyclerView Produk
        setupProduk()

        // 8. Setup Bottom Navigation
        setupBottomNavigation()
    }

    // ============== SETUP BOTTOM NAVIGATION ==============
    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Tandai menu "Beranda" sebagai menu yang sedang aktif
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Sudah di halaman beranda
                    true
                }
                R.id.nav_kategori -> {
                    // Pindah ke Halaman Kategori
                    val intent = Intent(this, KategoriActivity::class.java)
                    startActivity(intent)
                    finish() // Tutup Beranda agar tidak menumpuk di backstack
                    true
                }
                R.id.nav_transaksi -> {
                    Toast.makeText(this, "Buka Transaksi", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_chat -> {
                    Toast.makeText(this, "Buka Chat", Toast.LENGTH_SHORT).show()
                    true
                }
                // ✅ BAGIAN YANG DIUBAH: Pindah ke Halaman Profil Developer
                R.id.nav_akun -> {
                    val intent = Intent(this, ProfilDeveloperActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    // ============== SETUP KATEGORI ==============
    private fun setupKategori() {
        val listKategori = listOf(
            KategoriModel("Semen", android.R.drawable.ic_menu_agenda),
            KategoriModel("Besi", android.R.drawable.ic_menu_sort_by_size),
            KategoriModel("Cat", android.R.drawable.ic_menu_edit),
            KategoriModel("Kayu", android.R.drawable.ic_menu_gallery),
            KategoriModel("Pipa", android.R.drawable.ic_menu_compass),
            KategoriModel("Keramik", android.R.drawable.ic_menu_view),
            KategoriModel("Pasir", android.R.drawable.ic_menu_mapmode),
            KategoriModel("Bata", android.R.drawable.ic_menu_save)
        )

        rvKategori.layoutManager = GridLayoutManager(this, 4)
        rvKategori.adapter = KategoriAdapter(listKategori) { kategori ->
            Toast.makeText(this, "Kategori: ${kategori.nama}", Toast.LENGTH_SHORT).show()
        }
    }

    // ============== SETUP FLASH SALE ==============
    private fun setupFlashSale() {
        val listFlashSale = listOf(
            ProdukModel(
                nama = "Semen Tiga Roda 50kg",
                harga = "Rp 65.000",
                hargaCoret = "Rp 80.000",
                diskon = "18%",
                gambarResId = R.drawable.semen_3_rode,
                isFlashSale = true
            ),
            ProdukModel(
                nama = "Cat Tembok Dulux 5kg",
                harga = "Rp 120.000",
                hargaCoret = "Rp 150.000",
                diskon = "20%",
                gambarResId = android.R.drawable.ic_menu_gallery,
                isFlashSale = true
            ),
            ProdukModel(
                nama = "Besi Beton 10mm",
                harga = "Rp 85.000",
                hargaCoret = "Rp 100.000",
                diskon = "15%",
                gambarResId = android.R.drawable.ic_menu_gallery,
                isFlashSale = true
            ),
            ProdukModel(
                nama = "Pipa PVC 3 inch",
                harga = "Rp 45.000",
                hargaCoret = "Rp 55.000",
                diskon = "10%",
                gambarResId = android.R.drawable.ic_menu_gallery,
                isFlashSale = true
            )
        )

        rvFlashSale.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFlashSale.adapter = ProdukAdapter(listFlashSale) { produk ->
            Toast.makeText(this, "Beli: ${produk.nama}", Toast.LENGTH_SHORT).show()
        }
    }

    // ============== SETUP PRODUK TERLARIS ==============
    private fun setupProduk() {
        val listProduk = listOf(
            ProdukModel(
                nama = "Semen Gresik 40kg",
                harga = "Rp 58.000",
                gambarResId = android.R.drawable.ic_menu_gallery,
                rating = "4.9",
                terjual = "500+"
            ),
            ProdukModel(
                nama = "Besi Ulir 12mm SNI",
                harga = "Rp 95.000",
                gambarResId = android.R.drawable.ic_menu_gallery,
                rating = "4.8",
                terjual = "320+"
            ),
            ProdukModel(
                nama = "Cat Avian 1kg",
                harga = "Rp 45.000",
                gambarResId = android.R.drawable.ic_menu_gallery,
                rating = "4.7",
                terjual = "280+"
            ),
            ProdukModel(
                nama = "Keramik Roman 40x40",
                harga = "Rp 55.000",
                gambarResId = android.R.drawable.ic_menu_gallery,
                rating = "4.9",
                terjual = "410+"
            ),
            ProdukModel(
                nama = "Pipa Paralon 2 inch",
                harga = "Rp 35.000",
                gambarResId = android.R.drawable.ic_menu_gallery,
                rating = "4.6",
                terjual = "150+"
            ),
            ProdukModel(
                nama = "Triplek 9mm",
                harga = "Rp 110.000",
                gambarResId = android.R.drawable.ic_menu_gallery,
                rating = "4.8",
                terjual = "220+"
            )
        )

        rvProduk.layoutManager = GridLayoutManager(this, 2)
        rvProduk.adapter = ProdukAdapter(listProduk) { produk ->
            Toast.makeText(this, "Beli: ${produk.nama}", Toast.LENGTH_SHORT).show()
        }
    }
}