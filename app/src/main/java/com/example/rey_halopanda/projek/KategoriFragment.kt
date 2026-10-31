package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class KategoriFragment : Fragment() {

    private lateinit var rvKategoriGrid: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_kategori, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvKategoriGrid = view.findViewById(R.id.rvKategoriGrid)

        val etSearch = view.findViewById<EditText>(R.id.etSearchKategori)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(requireContext(), "Mencari: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            false
        }

        view.findViewById<View>(R.id.btnMintaPenawaran).setOnClickListener {
            Toast.makeText(requireContext(), "Form Penawaran Proyek...", Toast.LENGTH_SHORT).show()
        }

        setupGridKategori()
    }

    private fun setupGridKategori() {
        // Daftar 50 nama kategori/sub-kategori bahan bangunan yang realistis
        val daftarNamaKategori = listOf(
            "Semen Portland", "Semen Mortar Instan", "Besi Beton Ulir 8mm", "Besi Beton Ulir 10mm",
            "Besi Beton Ulir 12mm", "Besi Beton Polos 6mm", "Baja Ringan C75", "Baja Ringan C100",
            "Bata Merah Tradisional", "Bata Ringan Hebel", "Paving Block K200", "Paving Block K300",
            "Cat Tembok Interior", "Cat Tembok Eksterior", "Cat Minyak Kayu", "Pelapis Anti Bocor",
            "Keramik Lantai 30x30", "Keramik Lantai 40x40", "Granit 60x60", "Kayu Meranti",
            "Kayu Kamper", "Triplek 4mm", "Triplek 9mm", "Multiplek 12mm",
            "Genteng Beton", "Genteng Metal", "Spandek Gelombang", "Pipa PVC 1/2 inch",
            "Pipa PVC 3/4 inch", "Pipa PVC 1 inch", "Pipa PVC 2 inch", "Kran Air Stainless",
            "Closet Duduk Monoblok", "Wastafel Kaki", "Shower Head", "Bor Listrik Tangan",
            "Gergaji Mesin", "Gerinda Tangan", "Las Listrik Inverter", "Kompresor Angin",
            "Kabel NYM 2x1.5", "Kabel NYM 2x2.5", "Saklar & Stop Kontak", "Lampu LED Bulb",
            "Lampu Downlight", "Paku Beton & Dynabolt", "Baut, Mur & Ring", "Lem & Perekat Konstruksi",
            "Alat Pelindung Diri (APD)", "Perkakas Tangan (Obeng, Palu)"
        )

        val listKategori = mutableListOf<KategoriGridModel>()

        // Loop untuk membuat 50 data dummy kategori secara otomatis
        for (i in 1..50) {
            val nama = daftarNamaKategori.getOrNull(i - 1) ?: "Kategori Bangunan $i"

            // Jumlah produk bervariasi secara deterministik antara 20 hingga 270 produk
            val jumlahProduk = 20 + (i * 7) % 250
            val jumlahFormatted = "$jumlahProduk Produk"

            listKategori.add(
                KategoriGridModel(
                    nama = nama,
                    gambar = android.R.drawable.ic_menu_gallery,
                    jumlah = jumlahFormatted
                )
            )
        }

        rvKategoriGrid.layoutManager = GridLayoutManager(requireContext(), 2)

        rvKategoriGrid.adapter = object : RecyclerView.Adapter<KategoriGridVH>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriGridVH {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_kategori_grid, parent, false)
                return KategoriGridVH(v)
            }

            override fun onBindViewHolder(holder: KategoriGridVH, position: Int) {
                holder.bind(listKategori[position])
            }

            override fun getItemCount() = listKategori.size
        }
    }

    data class KategoriGridModel(
        val nama: String,
        val gambar: Int,
        val jumlah: String
    )

    inner class KategoriGridVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivKat: ImageView = itemView.findViewById(R.id.ivKatGrid)
        private val tvNama: TextView = itemView.findViewById(R.id.tvNamaKatGrid)
        private val tvJumlah: TextView = itemView.findViewById(R.id.tvJumlahKatGrid)
        private val tvLihat: TextView = itemView.findViewById(R.id.tvLihatKatGrid)

        fun bind(data: KategoriGridModel) {
            ivKat.setImageResource(data.gambar)
            tvNama.text = data.nama
            tvJumlah.text = data.jumlah

            itemView.setOnClickListener {
                Toast.makeText(requireContext(), "Buka Kategori: ${data.nama}", Toast.LENGTH_SHORT).show()
            }

            tvLihat.setOnClickListener {
                Toast.makeText(requireContext(), "Lihat semua produk di: ${data.nama}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}