package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R
import java.text.NumberFormat
import java.util.Locale

class CheckoutFragment : Fragment() {

    private var ongkir: Int = 0
    private var subtotal: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_checkout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvCheckout = view.findViewById<RecyclerView>(R.id.rvCheckoutProduk)
        val tvSubtotal = view.findViewById<TextView>(R.id.tvSubtotal)
        val tvOngkir = view.findViewById<TextView>(R.id.tvOngkir)
        val tvTotalTagihan = view.findViewById<TextView>(R.id.tvTotalTagihan)
        val tvTotalBayarBottom = view.findViewById<TextView>(R.id.tvTotalBayarBottom)
        val rgKurir = view.findViewById<android.widget.RadioGroup>(R.id.rgKurir)
        val rbInstan = view.findViewById<RadioButton>(R.id.rbInstan)

        // Tombol Back
        view.findViewById<ImageView>(R.id.btnBackCheckout).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Setup RecyclerView
        rvCheckout.layoutManager = LinearLayoutManager(requireContext())
        rvCheckout.adapter = CheckoutAdapter(CartManager.daftarKeranjang)

        // Hitung Subtotal
        subtotal = CartManager.hitungTotal()

        // Format Rupiah Helper
        fun formatRupiah(angka: Int): String {
            val localeID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            return formatRupiah.format(angka).replace("Rp", "Rp ").split(",")[0]
        }

        // Update Tampilan Awal
        tvSubtotal.text = formatRupiah(subtotal)
        tvOngkir.text = formatRupiah(ongkir)
        tvTotalTagihan.text = formatRupiah(subtotal + ongkir)
        tvTotalBayarBottom.text = formatRupiah(subtotal + ongkir)

        // Listener Radio Button Kurir
        rgKurir.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == rbInstan.id) {
                ongkir = 20000 // Ongkir Kurir Instan
            } else {
                ongkir = 0 // Truk Toko Gratis
            }
            tvOngkir.text = formatRupiah(ongkir)
            tvTotalTagihan.text = formatRupiah(subtotal + ongkir)
            tvTotalBayarBottom.text = formatRupiah(subtotal + ongkir)
        }

        // Aksi Tombol Buat Pesanan (DIUBAH)
        view.findViewById<View>(R.id.btnBuatPesanan).setOnClickListener {
            if (CartManager.daftarKeranjang.isEmpty()) {
                Toast.makeText(requireContext(), "Keranjang Kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Pindah ke Halaman Pembayaran
            val fragment = PembayaranFragment()

            // Bawa data total tagihan ke halaman pembayaran
            val bundle = Bundle()
            bundle.putString("EXTRA_TOTAL_TAGIHAN", formatRupiah(subtotal + ongkir))
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    // ================= ADAPTER CHECKOUT =================
    inner class CheckoutAdapter(private val list: List<CartItem>) : RecyclerView.Adapter<CheckoutAdapter.VH>() {

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvNama: TextView = itemView.findViewById(R.id.tvNamaCheckout)
            val tvQty: TextView = itemView.findViewById(R.id.tvQtyCheckout)
            val tvHarga: TextView = itemView.findViewById(R.id.tvHargaCheckout)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_produk, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val item = list[position]
            holder.tvNama.text = item.produk.nama
            holder.tvQty.text = "x ${item.jumlah}"

            // Ambil harga bersih
            val hargaBersih = item.produk.harga.replace("Rp ", "").replace(".", "").trim().toIntOrNull() ?: 0
            val totalHarga = hargaBersih * item.jumlah

            val localeID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            holder.tvHarga.text = formatRupiah.format(totalHarga).replace("Rp", "Rp ").split(",")[0]
        }

        override fun getItemCount() = list.size
    }
}