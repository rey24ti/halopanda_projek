package com.example.rey_halopanda.projek.adapter // Sesuaikan dengan folder adapter kamu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// ⚠️ PERHATIKAN IMPORT DI BAWAH INI (Tanpa .projek) ⚠️
import com.example.rey_halopanda.databinding.ItemMaterialBinding
import com.example.rey_halopanda.projek.ProdukModel

class MaterialAdapter(
    private val materialList: List<ProdukModel>,
    private val onItemClick: (ProdukModel) -> Unit
) : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    inner class MaterialViewHolder(val binding: ItemMaterialBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val binding = ItemMaterialBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        val item = materialList[position]
        with(holder.binding) {
            tvNamaMaterial.text = item.nama
            tvHarga.text = item.harga
            tvSatuan.text = "per unit"

            // Gambar lokal
            imgMaterial.setImageResource(item.gambarResId)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = materialList.size
}