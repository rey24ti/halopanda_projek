package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar // Wajib di-import untuk Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// Pastikan import R ini sesuai dengan nama package aplikasi Anda.
// Jika masih merah, coba hapus baris ini, lalu klik 'R' di bawah dan tekan Alt+Enter -> Import class
import com.example.rey_halopanda.R

// Catatan: Sebaiknya nama class diawali huruf kapital, misal: BeradaActivity
class beradaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_berada)

        // 1. SETUP TOOLBAR (Sesuai Gambar 1)
        // Pastikan di activity_berada.xml ada komponen dengan android:id="@+id/toolbar"
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            // Menghilangkan judul default agar UI Search Bar kita terlihat bersih
            supportActionBar?.title = ""
        }

        // 2. HANDLING WINDOW INSETS (Edge to Edge)
        // PENTING: Pastikan root layout di activity_berada.xml memiliki android:id="@+id/main"
        // Jika tidak ada, baris ini akan menyebabkan aplikasi CRASH (NullPointerException)
        val rootView = findViewById<android.view.View>(R.id.main)
        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    // 3. MENU TOOLBAR (Opsional, jika Anda ingin menambahkan ikon di pojok kanan Toolbar)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Jika Anda membuat file res/menu/menu_main.xml, hilangkan komentar di bawah:
        // menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Contoh handling jika ikon diklik
        return when (item.itemId) {
            // R.id.action_cart -> {
            //     Toast.makeText(this, "Keranjang diklik", Toast.LENGTH_SHORT).show()
            //     true
            // }
            else -> super.onOptionsItemSelected(item)
        }
    }
}