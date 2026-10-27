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
import com.example.rey_halopanda.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale

class KalkulatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_kalkulator, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etPanjang = view.findViewById<TextInputEditText>(R.id.etPanjang)
        val etLebar = view.findViewById<TextInputEditText>(R.id.etLebar)
        val btnHitung = view.findViewById<MaterialButton>(R.id.btnHitung)

        val layoutHasil = view.findViewById<LinearLayout>(R.id.layoutHasil)
        val tvLuas = view.findViewById<TextView>(R.id.tvLuas)
        val tvSemen = view.findViewById<TextView>(R.id.tvSemen)
        val tvPasir = view.findViewById<TextView>(R.id.tvPasir)
        val tvKeramik = view.findViewById<TextView>(R.id.tvKeramik)
        val tvBata = view.findViewById<TextView>(R.id.tvBata)
        val tvBesi = view.findViewById<TextView>(R.id.tvBesi)
        val tvCat = view.findViewById<TextView>(R.id.tvCat)
        val tvTotalBiaya = view.findViewById<TextView>(R.id.tvTotalBiaya)
        val btnBeliMaterial = view.findViewById<MaterialButton>(R.id.btnBeliMaterial)

        // Tombol Back
        view.findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnHitung.setOnClickListener {
            val strPanjang = etPanjang.text.toString()
            val strLebar = etLebar.text.toString()

            if (strPanjang.isEmpty() || strLebar.isEmpty()) {
                Toast.makeText(requireContext(), "Panjang dan Lebar wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val panjang = strPanjang.toDouble()
            val lebar = strLebar.toDouble()
            val luas = panjang * lebar

            // ================= LOGIKA PERHITUNGAN MATERIAL & BIAYA =================
            // 1. FAKTOR CADANGAN (Misal: kita tambahkan 5% - 10% untuk material terbuang)
            val faktorCadangan = 1.05 // 5% tambahan

            // 2. PERHITUNGAN KUANTITAS FISIK
            // Semen: Asumsi 1 sak 50kg untuk ~5 m2. (Luas / 5) * Cadangan
            val qtySemen = Math.ceil((luas / 5.0) * faktorCadangan).toInt()

            // Pasir: Asumsi 0.02 m3 per m2. * Cadangan
            val qtyPasir = luas * 0.02 * faktorCadangan

            // Keramik: 1 dus isi 1.44 m2 (40x40cm). * Cadangan
            val qtyKeramik = Math.ceil((luas / 1.44) * faktorCadangan).toInt()

            // Bata Merah: Asumsi 70 buah per m2. * Cadangan
            val qtyBata = Math.ceil((luas * 70) * faktorCadangan).toInt()

            // Besi Beton: Asumsi 4 batang per m2. * Cadangan
            val qtyBesi = Math.ceil((luas * 4) * faktorCadangan).toInt()

            // Cat Tembok: Asumsi 1 kg untuk 10 m2 (2 lapis). * Cadangan
            val qtyCat = Math.ceil((luas / 10.0) * 2 * faktorCadangan).toInt()

            // 3. PERHITUNGAN BIAYA (Harga Dummy)
            val hargaSemen = 65000
            val hargaPasir = 250000.0
            val hargaKeramik = 55000
            val hargaBata = 1000
            val hargaBesi = 50000
            val hargaCat = 40000

            val totalBiaya = (qtySemen * hargaSemen) +
                    (qtyPasir * hargaPasir) +
                    (qtyKeramik * hargaKeramik) +
                    (qtyBata * hargaBata) +
                    (qtyBesi * hargaBesi) +
                    (qtyCat * hargaCat)

            // Format Rupiah
            val localeID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            val totalBiayaFormat = formatRupiah.format(totalBiaya).replace("Rp", "Rp ").split(",")[0]

            // ================= TAMPILKAN HASIL =================
            tvLuas.text = "Luas Area: ${String.format("%.2f", luas)} m²"
            tvSemen.text = "• Semen: $qtySemen Sak (50kg)"
            tvPasir.text = "• Pasir: ${String.format("%.2f", qtyPasir)} m³"
            tvKeramik.text = "• Keramik: $qtyKeramik Dus (40x40cm)"
            tvBata.text = "• Bata Merah: $qtyBata Buah"
            tvBesi.text = "• Besi Beton: $qtyBesi Batang"
            tvCat.text = "• Cat Tembok: $qtyCat kg"
            tvTotalBiaya.text = totalBiayaFormat

            layoutHasil.visibility = View.VISIBLE
        }

        btnBeliMaterial.setOnClickListener {
            Toast.makeText(requireContext(), "Rincian belanja material siap dimasukkan ke keranjang!", Toast.LENGTH_SHORT).show()
            // Di sini logikanya bisa diarahkan ke KeranjangFragment atau Checkout
        }
    }
}