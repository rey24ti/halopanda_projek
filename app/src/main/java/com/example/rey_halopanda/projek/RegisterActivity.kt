package com.example.rey_halopanda.projek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rey_halopanda.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 1. Inisialisasi Komponen
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etNoHp = findViewById<EditText>(R.id.etNoHp)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etKonfirmasiPassword = findViewById<EditText>(R.id.etKonfirmasiPassword)
        val btnDaftar = findViewById<Button>(R.id.btnDaftar)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        // 2. Logika Tombol Daftar
        btnDaftar.setOnClickListener {
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()
            val noHp = etNoHp.text.toString() // Ambil nilai No HP
            val password = etPassword.text.toString()
            val konfirmasi = etKonfirmasiPassword.text.toString()

            // Validasi sederhana (Cek apakah ada yang kosong)
            if (nama.isEmpty() || email.isEmpty() || noHp.isEmpty() || password.isEmpty() || konfirmasi.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua data!", Toast.LENGTH_SHORT).show()
            } else if (password != konfirmasi) {
                Toast.makeText(this, "Password dan Konfirmasi tidak sama!", Toast.LENGTH_SHORT).show()
            } else {
                // Jika berhasil, tampilkan pesan dan pindah ke halaman Login
                Toast.makeText(this, "Registrasi Berhasil! Silakan Login.", Toast.LENGTH_LONG).show()

                // Pindah ke LoginActivity (Perhatikan huruf kapital 'L' di LoginActivity)
                val intent = Intent(this@RegisterActivity, loginActivity::class.java)
                startActivity(intent)
                finish() // Menutup halaman register agar tidak bisa di-back
            }
        }

        // 3. Logika Teks "Login di sini"
        tvLogin.setOnClickListener {
            // Kembali ke halaman Login
            val intent = Intent(this@RegisterActivity, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}