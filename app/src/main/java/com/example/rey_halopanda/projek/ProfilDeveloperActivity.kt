package com.example.rey_halopanda.projek

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rey_halopanda.R
import com.google.android.material.card.MaterialCardView

class ProfilDeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_developer)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol Back
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Media Sosial (Menggunakan MaterialCardView, bukan LinearLayout lagi)
        findViewById<MaterialCardView>(R.id.btnGithub).setOnClickListener {
            bukaUrl("https://github.com/rey_halopanda")
        }
        findViewById<MaterialCardView>(R.id.btnLinkedin).setOnClickListener {
            bukaUrl("https://linkedin.com/in/reyhalopanda")
        }
        findViewById<MaterialCardView>(R.id.btnInstagram).setOnClickListener {
            bukaUrl("https://instagram.com/rey_halopanda")
        }
    }

    private fun bukaUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Tidak dapat membuka link: $url", Toast.LENGTH_SHORT).show()
        }
    }
}