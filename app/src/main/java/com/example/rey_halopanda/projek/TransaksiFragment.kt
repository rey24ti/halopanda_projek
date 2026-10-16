package com.example.rey_halopanda.projek

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class TransaksiFragment : Fragment() {

    private lateinit var rvTransaksiList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transaksi, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTransaksiList = view.findViewById(R.id.rvTransaksiList)

        val etSearch = view.findViewById<EditText>(R.id.etSearchTrans)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(requireContext(), "Mencari: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            false
        }


        view.findViewById<View>(R.id.bannerFaktur).setOnClickListener {
            Toast.makeText(requireContext(), "Mengunduh Faktur...", Toast.LENGTH_SHORT).show()
        }

        setupListTransaksi()
    }

    private fun setupListTransaksi() {
        val dataTransaksi = listOf(
            TransModel(
                tanggal = "28 Feb 2026",
                invoice = "INV/20260228/XYZ123",
                status = "Selesai",
                namaProduk = "Semen Gresik PCC 40kg",
                varian = "40 Kg (1 Sak) Truk Coli",
                qty = "x 40 Sak",
                total = "Rp 4.280.000",
                warnaBgStatus = "#E8F5E9",
                warnaTextStatus = "#2E7D32",
                aksi1 = "Lacak Pesanan",
                aksi2 = "Beri Ulasan"
            ),
            TransModel(
                tanggal = "27 Feb 2026",
                invoice = "INV/20260227/ABC456",
                status = "Diproses",
                namaProduk = "Baja Ringan Canal C 0.75mm",
                varian = "0.75mm (1 Batang)",
                qty = "x 50 Batang",
                total = "Rp 10.450.000",
                warnaBgStatus = "#E3F2FD",
                warnaTextStatus = "#1565C0",
                aksi1 = "Hubungi Penjual",
                aksi2 = "Bayar Sekarang"
            ),
            TransModel(
                tanggal = "26 Feb 2026",
                invoice = "INV/20260226/DEF789",
                status = "Belum Dibayar",
                namaProduk = "Besi Beton Ulir D19 SJP 15m",
                varian = "D19 (1 Batang) 12m",
                qty = "x 100 Batang",
                total = "Rp 23.850.000",
                warnaBgStatus = "#FFEBEE",
                warnaTextStatus = "#C62828",
                aksi1 = "Rincian",
                aksi2 = "Bayar Sekarang"
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

    data class TransModel(
        val tanggal: String, val invoice: String, val status: String,
        val namaProduk: String, val varian: String, val qty: String,
        val total: String, val warnaBgStatus: String, val warnaTextStatus: String,
        val aksi1: String, val aksi2: String
    )

    inner class TransVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: TransModel) {
            itemView.findViewById<TextView>(R.id.tvTanggalTrans).text = data.tanggal
            itemView.findViewById<TextView>(R.id.tvInvoiceTrans).text = data.invoice

            val tvStatus = itemView.findViewById<TextView>(R.id.tvStatusTrans)
            tvStatus.text = data.status
            tvStatus.setBackgroundColor(Color.parseColor(data.warnaBgStatus))
            tvStatus.setTextColor(Color.parseColor(data.warnaTextStatus))

            itemView.findViewById<TextView>(R.id.tvNamaProdukTrans).text = data.namaProduk
            itemView.findViewById<TextView>(R.id.tvVarianTrans).text = data.varian
            itemView.findViewById<TextView>(R.id.tvQtyTrans).text = data.qty
            itemView.findViewById<TextView>(R.id.tvTotalTrans).text = data.total
            itemView.findViewById<ImageView>(R.id.ivProdukTrans)
                .setImageResource(android.R.drawable.ic_menu_gallery)

            val btn1 = itemView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnAksi1)
            val btn2 = itemView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnAksi2)

            btn1.text = data.aksi1
            btn2.text = data.aksi2

            btn1.setOnClickListener {
                Toast.makeText(requireContext(), "${data.aksi1} untuk ${data.invoice}", Toast.LENGTH_SHORT).show()
            }
            btn2.setOnClickListener {
                Toast.makeText(requireContext(), "${data.aksi2} untuk ${data.invoice}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}