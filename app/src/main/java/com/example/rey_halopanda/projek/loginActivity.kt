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
    private lateinit var etEmail: EditText // ID tetap etEmail, tapi fungsinya jadi kolom Nama
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var tvLupaPassword: TextView

    // ✅ Daftar NAMA yang diizinkan masuk
    private val daftarNamaDiizinkan = listOf(
        "owner",
        "karyawan",
        "admin",
        "budi",
        "rey"
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

        // Ubah hint dan tipe input agar sesuai sebagai kolom Nama
        etEmail.hint = "Masukkan Nama Anda"
        etEmail.inputType = android.text.InputType.TYPE_CLASS_TEXT
    }

    private fun setupListener() {
        btnLogin.setOnClickListener {
            val nama = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                nama.isEmpty() -> {
                    etEmail.error = "Nama tidak boleh kosong"
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
                    prosesLogin(nama, password)
                }
            }
        }

        etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                btnLogin.performClick()
                true
            } else {
                false
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this@loginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvLupaPassword.setOnClickListener {
            Toast.makeText(this, "Buka halaman Lupa Password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun prosesLogin(nama: String, password: String) {
        btnLogin.isEnabled = false
        btnLogin.text = "Loading..."

        btnLogin.postDelayed({
            if (daftarNamaDiizinkan.any { it.equals(nama, ignoreCase = true) }) {
                Toast.makeText(this, "Login Berhasil sebagai $nama", Toast.LENGTH_SHORT).show()
                hideKeyboard()

                // ================= PERUBAHAN DI SINI =================
                // Arahkan ke halaman Beranda (beradaActivity), bukan MainActivity
                val intent = Intent(this@loginActivity, beradaActivity::class.java)
                startActivity(intent)
                finish()
                // ======================================================

            } else {
                Toast.makeText(this, "Login Gagal! Nama tidak terdaftar.", Toast.LENGTH_LONG).show()
                etEmail.error = "Nama tidak terdaftar"
                etEmail.requestFocus()

                btnLogin.isEnabled = true
                btnLogin.text = "LOGIN"
            }
        }, 1000)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}