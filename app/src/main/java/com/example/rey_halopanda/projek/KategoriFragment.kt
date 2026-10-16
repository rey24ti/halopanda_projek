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

        val listKategori = listOf(
            KategoriGridModel("Semen & Mortar", android.R.drawable.ic_menu_gallery, "120 Produk"),
            KategoriGridModel("Besi & Baja Ringan", android.R.drawable.ic_menu_gallery, "85 Produk"),
            KategoriGridModel("Bata & Paving", android.R.drawable.ic_menu_gallery, "40 Produk"),
            KategoriGridModel("Cat & Anti Bocor", android.R.drawable.ic_menu_gallery, "110 Produk"),
            KategoriGridModel("Keramik & Granit", android.R.drawable.ic_menu_gallery, "75 Produk"),
            KategoriGridModel("Kayu & Triplek", android.R.drawable.ic_menu_gallery, "50 Produk"),
            KategoriGridModel("Atap & Spandek", android.R.drawable.ic_menu_gallery, "35 Produk"),
            KategoriGridModel("Pipa & Sanitari", android.R.drawable.ic_menu_gallery, "60 Produk"),
            KategoriGridModel("Perkakas & Mesin", android.R.drawable.ic_menu_gallery, "200 Produk"),
            KategoriGridModel("Alat Listrik & Lampu", android.R.drawable.ic_menu_gallery, "150 Produk")
        )

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
                Toast.makeText(requireContext(), "Lihat: ${data.nama}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}