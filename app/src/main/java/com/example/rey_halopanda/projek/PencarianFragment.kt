package com.example.rey_halopanda.projek

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class PencarianFragment : Fragment() {

    private lateinit var rvHasil: RecyclerView
    private lateinit var layoutEmpty: LinearLayout
    private lateinit var layoutRekomendasi: LinearLayout

    // Daftar semua produk (diambil dari data dummy Home)
    private val semuaProduk = listOf(
        ProdukModel("Semen Gresik 40kg", "Rp 58.000", gambarResId = android.R.drawable.ic_menu_gallery, rating = "4.9", terjual = "500+"),
        ProdukModel("Besi Ulir 12mm SNI", "Rp 95.000", gambarResId = android.R.drawable.ic_menu_gallery, rating = "4.8", terjual = "320+"),
        ProdukModel("Cat Avian 1kg", "Rp 45.000", gambarResId = android.R.drawable.ic_menu_gallery, rating = "4.7", terjual = "280+"),
        ProdukModel("Keramik Roman 40x40", "Rp 55.000", gambarResId = android.R.drawable.ic_menu_gallery, rating = "4.9", terjual = "410+"),
        ProdukModel("Pipa Paralon 2 inch", "Rp 35.000", gambarResId = android.R.drawable.ic_menu_gallery, rating = "4.6", terjual = "150+"),
        ProdukModel("Triplek 9mm", "Rp 110.000", gambarResId = android.R.drawable.ic_menu_gallery, rating = "4.8", terjual = "220+")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pencarian, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etSearch = view.findViewById<EditText>(R.id.etSearchInput)
        rvHasil = view.findViewById(R.id.rvHasilPencarian)
        layoutEmpty = view.findViewById(R.id.layoutEmpty)
        layoutRekomendasi = view.findViewById(R.id.layoutRekomendasi)

        // Tombol Back
        view.findViewById<ImageView>(R.id.btnBackSearch).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Setup RecyclerView
        rvHasil.layoutManager = GridLayoutManager(requireContext(), 2)

        // Listener saat user mengetik (Real-time Search)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString().trim()
                filterProduk(keyword)
            }
        })
    }

    private fun filterProduk(keyword: String) {
        if (keyword.isEmpty()) {
            // Jika kosong, sembunyikan hasil dan tampilkan rekomendasi
            layoutRekomendasi.visibility = View.VISIBLE
            rvHasil.visibility = View.GONE
            layoutEmpty.visibility = View.GONE
        } else {
            // Jika ada keyword, filter data
            layoutRekomendasi.visibility = View.GONE

            // Cari produk yang namanya mengandung keyword (case-insensitive)
            val hasilFilter = semuaProduk.filter {
                it.nama.contains(keyword, ignoreCase = true)
            }

            if (hasilFilter.isEmpty()) {
                // Jika tidak ada hasil
                rvHasil.visibility = View.GONE
                layoutEmpty.visibility = View.VISIBLE
            } else {
                // Jika ada hasil
                rvHasil.visibility = View.VISIBLE
                layoutEmpty.visibility = View.GONE

                rvHasil.adapter = ProdukAdapter(hasilFilter) {
                    Toast.makeText(requireContext(), "Beli: ${it.nama}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}