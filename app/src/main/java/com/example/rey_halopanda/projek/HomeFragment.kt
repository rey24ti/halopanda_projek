package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class HomeFragment : Fragment() {

    private lateinit var rvKategori: RecyclerView
    private lateinit var rvFlashSale: RecyclerView
    private lateinit var rvProduk: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvKategori  = view.findViewById(R.id.rvKategori)
        rvFlashSale = view.findViewById(R.id.rvFlashSale)
        rvProduk    = view.findViewById(R.id.rvProduk)


        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(requireContext(), "Mencari: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            false
        }

        view.findViewById<ImageView>(R.id.ivCart).setOnClickListener {
            Toast.makeText(requireContext(), "Buka Keranjang", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<ImageView>(R.id.ivNotif).setOnClickListener {
            Toast.makeText(requireContext(), "Buka Notifikasi", Toast.LENGTH_SHORT).show()
        }

        setupKategori()
        setupFlashSale()
        setupProduk()
    }

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
        rvKategori.layoutManager = GridLayoutManager(requireContext(), 4)
        rvKategori.adapter = KategoriAdapter(listKategori) {
            Toast.makeText(requireContext(), "Kategori: ${it.nama}", Toast.LENGTH_SHORT).show()
        }
    }

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
        rvFlashSale.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvFlashSale.adapter = ProdukAdapter(listFlashSale) {
            Toast.makeText(requireContext(), "Beli: ${it.nama}", Toast.LENGTH_SHORT).show()
        }
    }

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
        rvProduk.layoutManager = GridLayoutManager(requireContext(), 2)
        rvProduk.adapter = ProdukAdapter(listProduk) {
            Toast.makeText(requireContext(), "Beli: ${it.nama}", Toast.LENGTH_SHORT).show()
        }
    }
}