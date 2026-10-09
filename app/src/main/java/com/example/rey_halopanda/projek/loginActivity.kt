package com.example.rey_halopanda.projek

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rey_halopanda.R

class loginActivity : AppCompatActivity() {

    // Deklarasi view
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var tvLupaPassword: TextView

    // ✅ Daftar email yang diizinkan masuk
    private val daftarEmailDiizinkan = listOf(
        "owner@gmail",
        "karyawana@gmail"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Handle edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Init view
        initView()

        // Setup listener
        setupListener()
    }

    private fun initView() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvDaftarSekarang)
        tvLupaPassword = findViewById(R.id.tvLupaPassword)
    }

    private fun setupListener() {
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                email.isEmpty() -> {
                    etEmail.error = "Email tidak boleh kosong"
                    etEmail.requestFocus()
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    etEmail.error = "Format email tidak valid"
                    etEmail.requestFocus()
                }
                password.isEmpty() -> {
                    etPassword.error = "Password tidak boleh kosong"
                    etPassword.requestFocus()
                }
                password.length < 6 -> {
                    etPassword.error = "Password minimal 6 karakter"
                    etPassword.requestFocus()
                }
                else -> {
                    // Semua validasi lolos, jalankan proses login
                    prosesLogin(email, password)
                }
            }
        }

        // ✅ Deteksi tombol Enter di keyboard saat mengisi Password
        etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                btnLogin.performClick() // Otomatis klik tombol login
                true
            } else {
                false
            }
        }

        // --- BAGIAN INI YANG SAYA MODIFIKASI ---
        tvRegister.setOnClickListener {
            // Pindah ke halaman RegisterActivity
            // Pastikan nama class "RegisterActivity" sesuai dengan nama file Kotlin registrasi Anda
            val intent = Intent(this@loginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        // ----------------------------------------

        tvLupaPassword.setOnClickListener {
            // startActivity(Intent(this, lupaPasswordActivity::class.java))
            Toast.makeText(this, "Buka halaman Lupa Password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun prosesLogin(email: String, password: String) {
        // Nonaktifkan tombol & ubah teks saat loading
        btnLogin.isEnabled = false
        btnLogin.text = "Loading..."

        // Simulasi proses login (ganti dengan API/database nanti)
        btnLogin.postDelayed({
            // ✅ Cek apakah email ada di daftar yang diizinkan
            if (email in daftarEmailDiizinkan) {
                Toast.makeText(this, "Login Berhasil sebagai $email", Toast.LENGTH_SHORT).show()
                hideKeyboard()

                // TODO: Arahkan ke halaman utama (MainActivity) nanti
                // val intent = Intent(this, MainActivity::class.java)
                // startActivity(intent)
                // finish()
            } else {
                // ❌ Email tidak terdaftar
                Toast.makeText(this, "Login Gagal! Email tidak terdaftar.", Toast.LENGTH_LONG).show()
                etEmail.error = "Email tidak terdaftar"
                etEmail.requestFocus()
            }

            // Kembalikan tombol ke kondisi semula
            btnLogin.isEnabled = true
            btnLogin.text = "LOGIN"
        }, 1000)
    }

    // ✅ Fungsi untuk menyembunyikan keyboard
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}