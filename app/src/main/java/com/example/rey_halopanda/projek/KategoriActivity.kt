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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class KategoriActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kategori) // Memuat layout XML

        // Handling Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Setup Search Bar
        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(this, "Mencari: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            false
        }

        // 2. Setup Icon Cart & Notif
        findViewById<ImageView>(R.id.ivCart).setOnClickListener {
            Toast.makeText(this, "Buka Keranjang", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.ivNotif).setOnClickListener {
            Toast.makeText(this, "Buka Notifikasi", Toast.LENGTH_SHORT).show()
        }

        // 3. Setup RecyclerView Katalog (Vertikal)
        val rvKatalog = findViewById<RecyclerView>(R.id.rvKatalog)
        val listKatalog = listOf(
            KategoriKatalogModel("Semen, Pasir & Mortar", "Semen PCC, Pasir, dll.", android.R.drawable.ic_menu_agenda),
            KategoriKatalogModel("Besi, Baja & Logam", "Besi Beton, Baja Ringan", android.R.drawable.ic_menu_sort_by_size),
            KategoriKatalogModel("Bata, Batako & Dinding", "Bata Merah, Batako, Hebel", android.R.drawable.ic_menu_gallery),
            KategoriKatalogModel("Cat, Pelapis & Anti Bocor", "Cat Tembok, Waterproofing", android.R.drawable.ic_menu_edit),
            KategoriKatalogModel("Keramik, Granit & Lantai", "Granit Tile 60x60, dll.", android.R.drawable.ic_menu_view),
            KategoriKatalogModel("Kayu, Plywood & Papan", "Plywood, Multiplek, dll.", android.R.drawable.ic_menu_save),
            KategoriKatalogModel("Atap, Genteng & Baja", "Genteng Metal, Spandek", android.R.drawable.ic_menu_compass),
            KategoriKatalogModel("Pipa, Pompa & Sanitasi", "Pipa PVC, Fitting, dll.", android.R.drawable.ic_menu_mapmode),
            KategoriKatalogModel("Perkakas, Mesin & Alat", "Bor, Gerinda, Tang, dll.", android.R.drawable.ic_menu_manage)
        )

        rvKatalog.layoutManager = LinearLayoutManager(this)
        rvKatalog.adapter = KategoriKatalogAdapter(listKatalog) { kategori ->
            Toast.makeText(this, "Buka: ${kategori.title}", Toast.LENGTH_SHORT).show()
        }

        // 4. Setup Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_kategori // Set agar menu Kategori yang tersorot
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Kembali ke Beranda
                    startActivity(Intent(this, beradaActivity::class.java))
                    finish() // Tutup KategoriActivity
                    true
                }
                R.id.nav_kategori -> true // Sedang di halaman ini
                R.id.nav_transaksi -> {
                    Toast.makeText(this, "Transaksi", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_chat -> {
                    Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_akun -> {
                    Toast.makeText(this, "Akun", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}