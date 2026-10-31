package com.example.rey_halopanda.projek

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.rey_halopanda.R
import java.io.File
import java.io.FileOutputStream

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

        // Tombol Unduh PDF (Fitur Baru)
        view.findViewById<View>(R.id.btnDownloadPdf).setOnClickListener {
            generatePdf()
        }
    }

    // ================= FUNGSI PEMBUAT PDF =================
    private fun generatePdf() {

        val pdfDocument = PdfDocument()


        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas


        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 16f

        var y = 50f // Posisi Y awal
        val x = 40f // Posisi X (Margin kiri)
        val lineSpacing = 25f // Jarak antar baris

        paint.isFakeBoldText = true
        canvas.drawText("INVOICE TOKO BANGUNAN", x, y, paint)

        y += lineSpacing * 2
        paint.textSize = 12f
        paint.isFakeBoldText = false

        // Rincian Transaksi
        canvas.drawText("ID Transaksi : TRX-20261010-001", x, y, paint); y += lineSpacing
        canvas.drawText("Tanggal      : 10 Okt 2026", x, y, paint); y += lineSpacing
        canvas.drawText("Status       : LUNAS", x, y, paint); y += lineSpacing * 2

        // Produk
        canvas.drawText("Rincian Produk:", x, y, paint); y += lineSpacing
        canvas.drawText("- Semen Portland 50kg (2 sak) : Rp  50.000", x, y, paint); y += lineSpacing
        canvas.drawText("- Ongkos Kirim                 : Rp  75.000", x, y, paint); y += lineSpacing
        canvas.drawText("- Asuransi Pengiriman         : Rp   2.000", x, y, paint); y += lineSpacing * 2

        // Total
        paint.textSize = 14f
        paint.isFakeBoldText = true
        canvas.drawText("Total Pembayaran : Rp 127.000", x, y, paint)


        pdfDocument.finishPage(page)

        val fileName = "Invoice_TRX-001.pdf"
        val file = File(requireContext().getExternalFilesDir(null), fileName)

        try {

            val fileOutputStream = FileOutputStream(file)
            pdfDocument.writeTo(fileOutputStream)
            fileOutputStream.close()

            Toast.makeText(requireContext(), "PDF Berhasil disimpan!", Toast.LENGTH_SHORT).show()

            val uri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.provider",
                file
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            try {
                startActivity(intent) // Membuka PDF
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "Tidak ada aplikasi PDF Viewer di HP ini", Toast.LENGTH_LONG).show()
            }
            // ==========================================================

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Gagal menyimpan PDF: ${e.message}", Toast.LENGTH_SHORT).show()
        } finally {
            pdfDocument.close()
        }
    }
}