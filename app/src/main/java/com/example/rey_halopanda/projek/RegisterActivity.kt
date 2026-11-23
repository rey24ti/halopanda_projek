package com.example.rey_halopanda.projek

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.rey_halopanda.databinding.ActivityRegisterBinding
import com.example.rey_halopanda.projek.data.api.SupabaseClient
import com.example.rey_halopanda.projek.data.model.UserModel
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftar.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val noHp = binding.etNoHp.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Reset pesan error sebelumnya
            binding.tilNama.error = null
            binding.tilNoHp.error = null
            binding.tilEmail.error = null
            binding.tilPassword.error = null

            // Validasi input
            if (nama.isEmpty()) {
                binding.tilNama.error = "Nama tidak boleh kosong"
                return@setOnClickListener
            }
            if (noHp.isEmpty()) {
                binding.tilNoHp.error = "No HP tidak boleh kosong"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.tilEmail.error = "Email tidak boleh kosong"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.tilPassword.error = "Password tidak boleh kosong"
                return@setOnClickListener
            }

            prosesRegister(nama, noHp, email, password)
        }
    }

    private fun prosesRegister(nama: String, noHp: String, email: String, password: String) {
        binding.btnDaftar.isEnabled = false
        binding.btnDaftar.text = "Mendaftar..."

        lifecycleScope.launch {
            try {
                val userBaru = UserModel(
                    nama = nama,
                    no_hp = noHp,
                    email = email,
                    password = password
                )
                val response = SupabaseClient.apiService.registerUser(userBaru)

                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Register Berhasil!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val pesanAsli = response.errorBody()?.string()
                    Toast.makeText(
                        this@RegisterActivity,
                        "Kode ${response.code()}: $pesanAsli",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@RegisterActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.btnDaftar.isEnabled = true
                binding.btnDaftar.text = "Daftar"
            }
        }
    }
}