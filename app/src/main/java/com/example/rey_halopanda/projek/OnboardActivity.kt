package com.example.rey_halopanda.projek

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.rey_halopanda.databinding.ActivityOnboardBinding

class OnboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Daftar Fragment Onboard
        val fragments = listOf(
            Onboard1Fragment(),
            Onboard2Fragment(),
            Onboard3Fragment()
        )

        // Setup Adapter
        val adapter = OnboardAdapter(this, fragments)
        binding.onboardViewPager.adapter = adapter

        // Hubungkan DotsIndicator
        binding.dotsIndicator.attachTo(binding.onboardViewPager)

        // Logika Tombol Next / Mulai
        binding.btnNext.setOnClickListener {
            if (binding.onboardViewPager.currentItem + 1 < adapter.itemCount) {
                // Jika belum di halaman terakhir, geser ke halaman berikutnya
                binding.onboardViewPager.currentItem += 1
            } else {
                // Jika sudah di halaman terakhir, simpan status Onboard
                val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                sharedPref.edit().putBoolean("isOnboardShown", true).apply()

                // Pindah ke Halaman LOGIN (BUKAN MainActivity)
                // Pastikan nama loginActivity sesuai dengan nama class di file loginActivity.kt Anda
                startActivity(Intent(this, loginActivity::class.java))
                finish()
            }
        }

        // Mengubah teks tombol saat halaman berganti
        binding.onboardViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    binding.btnNext.text = "Mulai Belanja"
                } else {
                    binding.btnNext.text = "Selanjutnya"
                }
            }
        })
    }
}