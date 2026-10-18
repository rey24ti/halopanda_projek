package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rey_halopanda.R

class DetailTransaksiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail_transaksi, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol Back
        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Tombol Chat Penjual
        view.findViewById<View>(R.id.btnChatPenjual).setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Chat Penjual...", Toast.LENGTH_SHORT).show()
        }

        // Tombol Beli Lagi
        view.findViewById<View>(R.id.btnBeliLagi).setOnClickListener {
            Toast.makeText(requireContext(), "Menambahkan ke Keranjang...", Toast.LENGTH_SHORT).show()
        }

        // Tombol Lacak Pesanan
        view.findViewById<View>(R.id.btnLacak).setOnClickListener {
            Toast.makeText(requireContext(), "Melacak Pesanan...", Toast.LENGTH_SHORT).show()
        }

        // Tombol CS
        view.findViewById<View>(R.id.btnSuperCS).setOnClickListener {
            Toast.makeText(requireContext(), "Menghubungi CS...", Toast.LENGTH_SHORT).show()
        }
    }
}