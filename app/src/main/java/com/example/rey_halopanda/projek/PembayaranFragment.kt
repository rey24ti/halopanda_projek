package com.example.rey_halopanda.projek

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rey_halopanda.R
import com.example.rey_halopanda.databinding.FragmentPembayaranBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PembayaranFragment : Fragment() {

    private var _binding: FragmentPembayaranBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPembayaranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Terima Total Tagihan dari CheckoutFragment
        val totalTagihan = arguments?.getString("EXTRA_TOTAL_TAGIHAN") ?: "Rp 0"
        binding.tvTotalTagihan.text = totalTagihan

        // Tombol Back
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Logika Switch Metode Pembayaran
        binding.rgMetodeBayar.setOnCheckedChangeListener { _, checkedId ->
            // Sembunyikan semua layout terlebih dahulu
            binding.layoutVA.visibility = View.GONE
            binding.layoutQRIS.visibility = View.GONE
            binding.layoutCOD.visibility = View.GONE
            binding.layoutTempo.visibility = View.GONE
            binding.layoutManual.visibility = View.GONE

            // Tampilkan layout yang sesuai
            when (checkedId) {
                binding.rbVA.id -> {
                    binding.layoutVA.visibility = View.VISIBLE
                    binding.btnKonfirmasiBayar.text = "Konfirmasi Pembayaran VA"
                }
                binding.rbQRIS.id -> {
                    binding.layoutQRIS.visibility = View.VISIBLE
                    binding.btnKonfirmasiBayar.text = "Cek Status QRIS"
                }
                binding.rbCOD.id -> {
                    binding.layoutCOD.visibility = View.VISIBLE
                    binding.btnKonfirmasiBayar.text = "Buat Pesanan COD"
                }
                binding.rbTempo.id -> {
                    binding.layoutTempo.visibility = View.VISIBLE
                    binding.btnKonfirmasiBayar.text = "Gunakan Limit Tempo"
                }
                binding.rbManual.id -> {
                    binding.layoutManual.visibility = View.VISIBLE
                    binding.btnKonfirmasiBayar.text = "Saya Sudah Transfer"
                }
            }
        }

        // Tombol Copy VA
        binding.btnCopyVA.setOnClickListener {
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Nomor VA", binding.tvNomorVA.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Nomor VA berhasil disalin!", Toast.LENGTH_SHORT).show()
        }

        // Tombol Konfirmasi
        binding.btnKonfirmasiBayar.setOnClickListener {
            if (binding.rgMetodeBayar.checkedRadioButtonId == -1) {
                Toast.makeText(requireContext(), "Pilih metode pembayaran terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                tampilkanDialogSukses()
            }
        }
    }

    private fun tampilkanDialogSukses() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Pesanan Berhasil")
            .setMessage("Pembayaran Anda sedang diproses oleh sistem.")
            .setIcon(android.R.drawable.ic_dialog_info)
            .setPositiveButton("Kembali ke Beranda") { dialog, _ ->
                dialog.dismiss()

                // 2. Kosongkan Keranjang karena transaksi selesai
                CartManager.daftarKeranjang.clear()

                // 3. Kembali ke Halaman Utama (Home)
                val activity = requireActivity() as AppCompatActivity
                val bottomNav = activity.findViewById<BottomNavigationView>(R.id.bottomNav)
                bottomNav.selectedItemId = R.id.nav_home // Pindah ke tab Home

                // Hapus history fragment agar tidak bisa back ke halaman pembayaran
                parentFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            .setCancelable(false)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}