package com.example.rey_halopanda.projek

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.rey_halopanda.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // ✅ PENTING: Panggil installSplashScreen() SEBELUM super.onCreate()
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Jeda selama 2 detik (2000 milidetik)
        Handler(Looper.getMainLooper()).postDelayed({
            // Setelah 2 detik, pindah ke halaman Login
            val intent = Intent(this@SplashActivity, loginActivity::class.java)
            startActivity(intent)
            finish() // Tutup SplashActivity agar tidak bisa di-back
        }, 2000)
    }
}