package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R
import java.text.NumberFormat
import java.util.Locale

class KeranjangFragment : Fragment() {

    private lateinit var rvKeranjang: RecyclerView
    private lateinit var layoutKosong: LinearLayout
    private lateinit var layoutTotal: LinearLayout
    private lateinit var tvTotalBayar: TextView
    private lateinit var adapter: KeranjangAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_keranjang, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvKeranjang = view.findViewById(R.id.rvKeranjang)
        layoutKosong = view.findViewById(R.id.layoutKeranjangKosong)
        layoutTotal = view.findViewById(R.id.layoutTotal)
        tvTotalBayar = view.findViewById(R.id.tvTotalBayar)

        // Tombol Back
        view.findViewById<ImageView>(R.id.btnBackCart).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Setup Adapter
        adapter = KeranjangAdapter(CartManager.daftarKeranjang) { updateTampilan() }
        rvKeranjang.layoutManager = LinearLayoutManager(requireContext())
        rvKeranjang.adapter = adapter

        // Tombol Checkout
        view.findViewById<View>(R.id.btnCheckout).setOnClickListener {
            if (CartManager.daftarKeranjang.isEmpty()) {
                Toast.makeText(requireContext(), "Keranjang Kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val fragment = CheckoutFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        // Panggil sekali saat pertama kali dimuat
        updateTampilan()
    }

    // SATU FUNGSI UNTUK MENGATUR SEMUA TAMPILAN (Mencegah Infinite Loop)
    private fun updateTampilan() {
        // 1. Update Total Harga
        val total = CartManager.hitungTotal()
        val localeID = Locale("id", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        val totalFormat = formatRupiah.format(total).replace("Rp", "Rp ").split(",")[0]
        tvTotalBayar.text = totalFormat

        // 2. Cek Keranjang Kosong / Tidak
        if (CartManager.daftarKeranjang.isEmpty()) {
            layoutKosong.visibility = View.VISIBLE
            rvKeranjang.visibility = View.GONE
            layoutTotal.visibility = View.GONE
        } else {
            layoutKosong.visibility = View.GONE
            rvKeranjang.visibility = View.VISIBLE
            layoutTotal.visibility = View.VISIBLE
        }

        // 3. Beritahu Adapter kalau data berubah
        adapter.notifyDataSetChanged()
    }

    // ================= ADAPTER KERANJANG =================
    inner class KeranjangAdapter(
        private val list: List<CartItem>,
        private val onDataChanged: () -> Unit
    ) : RecyclerView.Adapter<KeranjangAdapter.VH>() {

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvNama: TextView = itemView.findViewById(R.id.tvNamaCart)
            val tvHarga: TextView = itemView.findViewById(R.id.tvHargaCart)
            val tvQty: TextView = itemView.findViewById(R.id.tvQtyCart)
            val btnMinus: TextView = itemView.findViewById(R.id.btnMinus)
            val btnPlus: TextView = itemView.findViewById(R.id.btnPlus)
            val ivProduk: ImageView = itemView.findViewById(R.id.ivProdukCart)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val item = list[position]
            holder.tvNama.text = item.produk.nama
            holder.tvHarga.text = item.produk.harga
            holder.tvQty.text = item.jumlah.toString()
            holder.ivProduk.setImageResource(android.R.drawable.ic_menu_gallery)

            // Aksi Tambah Qty (+)
            holder.btnPlus.setOnClickListener {
                item.jumlah++
                onDataChanged()
            }

            // Aksi Kurang Qty (-)
            holder.btnMinus.setOnClickListener {
                if (item.jumlah > 1) {
                    item.jumlah--
                    onDataChanged()
                } else {
                    // Hapus dari keranjang jika qty jadi 0
                    list.toMutableList().removeAt(position)
                    CartManager.daftarKeranjang.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount)
                    onDataChanged()
                }
            }
        }

        override fun getItemCount() = list.size
    }
}