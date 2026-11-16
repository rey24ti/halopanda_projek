package com.example.rey_halopanda.projek

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.rey_halopanda.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Sesuaikan dengan layout Splash Anda

        // Delay 2 detik
        Handler(Looper.getMainLooper()).postDelayed({

            val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val isOnboardShown = sharedPref.getBoolean("isOnboardShown", false)

            if (isOnboardShown) {
                // Jika sudah pernah melihat onboard, langsung ke Halaman Login
                startActivity(Intent(this, loginActivity::class.java))
            } else {
                // Jika belum pernah, tampilkan Onboard Screen
                startActivity(Intent(this, OnboardActivity::class.java))
            }

            finish() // Tutup Splash agar tidak bisa di-back

        }, 2000) // 2000 milidetik = 2 detik
    }
}