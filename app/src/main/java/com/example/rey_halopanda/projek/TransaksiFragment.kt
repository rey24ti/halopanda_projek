package com.example.rey_halopanda.projek

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R
import com.google.android.material.button.MaterialButton

class TransaksiFragment : Fragment() {

    private lateinit var rvTransaksiList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transaksi, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTransaksiList = view.findViewById(R.id.rvTransaksiList)

        // Setup Pencarian
        val etSearch = view.findViewById<EditText>(R.id.etSearchTrans)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(requireContext(), "Mencari: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            false
        }

        // Setup Tombol Tambah (FAB)
        view.findViewById<View>(R.id.fabAdd).setOnClickListener {
            Toast.makeText(requireContext(), "Buat Pesanan Baru...", Toast.LENGTH_SHORT).show()
        }

        setupListTransaksi()
    }

    private fun setupListTransaksi() {
        // Data Dummy (Tanpa btnUtamaText & btnUtamaIcon karena sudah otomatis)
        val dataTransaksi = listOf(
            TransModel(
                id = "TRX-20261010-001",
                tanggal = "1 Okt 2026",
                status = "Diproses",
                namaProduk = "Semen Portland 50kg",
                varian = "Tipe 1 PCC, 1 Sak",
                qty = "x 100 Sak",
                total = "Rp 75.000",
                showFaktur = true,
                warnaBg = "#E3F2FD", warnaText = "#0D47A1"
            ),
            TransModel(
                id = "TRX-20261010-004",
                tanggal = "4 Okt 2026",
                status = "Dikirim",
                namaProduk = "Besi Beton Ulir D13 SNI Full",
                varian = "Ulir 13mm x 12m",
                qty = "x 80 Batang",
                total = "Rp 750.000",
                showFaktur = false,
                warnaBg = "#F5F5F5", warnaText = "#424242"
            ),
            TransModel(
                id = "TRX-20261010-042",
                tanggal = "10 Okt 2026",
                status = "Selesai",
                namaProduk = "Cat Palfon Anti Bocor Waterproofing",
                varian = "Warna Abu-abu, 4kg",
                qty = "x 2 Kaleng",
                total = "Rp 60.000",
                showFaktur = true,
                warnaBg = "#E8F5E9", warnaText = "#2E7D32"
            )
        )

        rvTransaksiList.layoutManager = LinearLayoutManager(requireContext())

        rvTransaksiList.adapter = object : RecyclerView.Adapter<TransVH>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransVH {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_transaksi_card, parent, false)
                return TransVH(v)
            }

            override fun onBindViewHolder(holder: TransVH, position: Int) {
                holder.bind(dataTransaksi[position])
            }

            override fun getItemCount() = dataTransaksi.size
        }
    }

    // Model Data
    data class TransModel(
        val id: String, val tanggal: String, val status: String,
        val namaProduk: String, val varian: String, val qty: String,
        val total: String, val showFaktur: Boolean,
        val warnaBg: String, val warnaText: String
    )

    inner class TransVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: TransModel) {
            // 1. Set Data Teks
            itemView.findViewById<TextView>(R.id.tvTrxId).text = data.id
            itemView.findViewById<TextView>(R.id.tvTrxDate).text = data.tanggal

            // 2. Set Status (Teks & Warna Background)
            val tvStatus = itemView.findViewById<TextView>(R.id.tvStatus)
            tvStatus.text = data.status
            tvStatus.setTextColor(Color.parseColor(data.warnaText))

            val bgDrawable = when (data.warnaBg) {
                "#E3F2FD" -> R.drawable.bg_status_blue
                "#E8F5E9" -> R.drawable.bg_status_green
                else -> R.drawable.bg_status_gray
            }
            tvStatus.setBackgroundResource(bgDrawable)

            // 3. Tampilkan/Sembunyikan Banner Faktur
            val layoutFaktur = itemView.findViewById<LinearLayout>(R.id.layoutFaktur)
            layoutFaktur.visibility = if (data.showFaktur) View.VISIBLE else View.GONE

            // 4. Set Data Produk
            itemView.findViewById<TextView>(R.id.tvNamaProduk).text = data.namaProduk
            itemView.findViewById<TextView>(R.id.tvVarian).text = data.varian
            itemView.findViewById<TextView>(R.id.tvQty).text = data.qty
            itemView.findViewById<TextView>(R.id.tvTotal).text = data.total

            // 5. Tombol Detail (Navigasi ke DetailTransaksiFragment)
            itemView.findViewById<MaterialButton>(R.id.btnDetail).setOnClickListener {
                val fragment = DetailTransaksiFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            // ================= 6. LOGIKA TOMBOL DINAMIS =================
            val btnUtama = itemView.findViewById<MaterialButton>(R.id.btnAksiUtama)

            // Jika status Selesai atau Batal, jadi "Beli Lagi". Jika masih jalan, jadi "Lacak Pesanan".
            if (data.status == "Selesai" || data.status == "Dibatalkan") {
                btnUtama.text = "Beli Lagi"
                btnUtama.setIconResource(R.drawable.ic_cart)
            } else {
                btnUtama.text = "Lacak Pesanan"
                btnUtama.setIconResource(R.drawable.ic_location)
            }

            btnUtama.setOnClickListener {
                Toast.makeText(requireContext(), "${btnUtama.text} untuk ${data.id}", Toast.LENGTH_SHORT).show()
            }
            // ============================================================
        }
    }
}