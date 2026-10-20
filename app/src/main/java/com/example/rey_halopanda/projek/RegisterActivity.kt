package com.example.rey_halopanda.projek

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rey_halopanda.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNama = findViewById<TextInputEditText>(R.id.etNama)
        val etNoHp = findViewById<TextInputEditText>(R.id.etNoHp)
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etKonfirmasi = findViewById<TextInputEditText>(R.id.etKonfirmasiPassword)
        val btnDaftar = findViewById<MaterialButton>(R.id.btnDaftar)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnDaftar.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val noHp = etNoHp.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val konfirmasi = etKonfirmasi.text.toString().trim()


            if (nama.isEmpty() || noHp.isEmpty() || username.isEmpty() || password.isEmpty() || konfirmasi.isEmpty()) {
                Toast.makeText(this, "Semua kolom wajib diisi!", Toast.LENGTH_SHORT).show()
            } else if (noHp.length < 10) {
                Toast.makeText(this, "Nomor HP tidak valid (minimal 10 digit)!", Toast.LENGTH_SHORT).show()
            } else if (password != konfirmasi) {
                Toast.makeText(this, "Password dan Konfirmasi tidak cocok!", Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, VerifikasiActivity::class.java)
                intent.putExtra("EXTRA_NO_HP", noHp)
                intent.putExtra("EXTRA_PASSWORD", password)
                intent.putExtra("EXTRA_USERNAME", username)
                startActivity(intent)
                finish()
            }
        }

        tvLogin.setOnClickListener {
            finish()
        }
    }
}