package com.example.rey_halopanda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rey_halopanda.databinding.ActivityMainBinding // Pastikan import ini ada
import com.example.rey_halopanda.projek.beradaActivity
import com.example.rey_halopanda.projek.loginActivity

class MainActivity : AppCompatActivity() {

    // 1. Deklarasi binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. INISIALISASI BINDING (Ini yang mungkin hilang di kode Anda)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Gunakan binding.root, bukan R.layout.activity_main

        // 3. Baru kemudian gunakan binding untuk tombol
        binding.btnProjekUas.setOnClickListener {
            val intent = Intent(this@MainActivity, loginActivity::class.java)
            startActivity(intent)
        }

        binding.btnpelangg.setOnClickListener {
            val intent = Intent(this@MainActivity, beradaActivity::class.java)
            startActivity(intent)
        }
    }
}