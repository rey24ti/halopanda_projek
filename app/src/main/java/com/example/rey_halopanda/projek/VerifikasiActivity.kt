package com.example.rey_halopanda.projek

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rey_halopanda.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class VerifikasiActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // Ambil data dari Intent
        val noHpTerdaftar = intent.getStringExtra("EXTRA_NO_HP") ?: ""
        val passwordTerdaftar = intent.getStringExtra("EXTRA_PASSWORD") ?: ""
        val usernameTerdaftar = intent.getStringExtra("EXTRA_USERNAME") ?: ""

        // ================== TAMPILKAN NOTIFIKASI OTP DI SINI ==================
        // Memunculkan pop-up saat halaman OTP dibuka
        if (noHpTerdaftar.isNotEmpty()) {
            showOtpNotification(noHpTerdaftar)
        }
        // ======================================================================

        val etOtp = findViewById<TextInputEditText>(R.id.etOtp)
        val btnVerifikasi = findViewById<MaterialButton>(R.id.btnVerifikasi)

        btnVerifikasi.setOnClickListener {
            val inputOtp = etOtp.text.toString().trim()

            // Cek OTP Kosong atau Salah
            if (inputOtp.isEmpty() || inputOtp != noHpTerdaftar) {
                showErrorDialog()
            } else {
                // Jika OTP Benar, Simpan ke SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("KEY_USERNAME", usernameTerdaftar)
                editor.putString("KEY_PASSWORD", passwordTerdaftar)
                editor.apply()

                Toast.makeText(this, "Verifikasi Berhasil! Silakan Login.", Toast.LENGTH_SHORT).show()

                // Pindah ke Halaman Login
                val intent = Intent(this, loginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    // Fungsi Pop-up Notifikasi Pesan (Simulasi SMS)
    private fun showOtpNotification(noHp: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Notifikasi Pesan")
            .setMessage("Toko Bangunan:\n\nKode OTP registrasi Anda adalah:\n$noHp\n\nJangan berikan kode ini kepada siapa pun.")
            .setIcon(android.R.drawable.ic_dialog_email)
            .setCancelable(false) // User wajib klik tombol
            .setPositiveButton("Verifikasi Sekarang") { dialog, _ ->
                dialog.dismiss() // Menutup pop-up saat tombol diklik
            }
            .show()
    }

    // Fungsi Error jika OTP salah
    private fun showErrorDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Verifikasi Gagal")
            .setMessage("Kode OTP salah atau kosong. Pastikan Anda memasukkan Nomor HP yang benar saat registrasi.")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Coba Lagi") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}