package com.example.rey_halopanda.projek

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class MaterialAdapter(
    private val list: List<ProdukModel>,
    private val onAddClick: (ProdukModel) -> Unit
) : RecyclerView.Adapter<MaterialAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvNama: TextView = v.findViewById(R.id.tvNamaProduk)
        val tvHarga: TextView = v.findViewById(R.id.tvHargaProduk)
        val imgProduk: ImageView = v.findViewById(R.id.ivProduk)
        val tvFlashSale: TextView = v.findViewById(R.id.tvFlashSaleTag)
        val tvHargaCoret: TextView = v.findViewById(R.id.tvHargaCoret)
        val tvRating: TextView = v.findViewById(R.id.tvRating)
        val tvTerjual: TextView = v.findViewById(R.id.tvTerjual)
        val btnBeli: View = v.findViewById(R.id.btnBeli)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]

        holder.tvNama.text = item.nama
        holder.tvHarga.text = item.harga
        holder.imgProduk.setImageResource(item.gambarResId)

        // Cek Flash Sale
        if (item.isFlashSale) {
            holder.tvFlashSale.visibility = View.VISIBLE
        } else {
            holder.tvFlashSale.visibility = View.GONE
        }

        // Cek Harga Coret
        if (item.hargaCoret != null) {
            holder.tvHargaCoret.visibility = View.VISIBLE
            holder.tvHargaCoret.text = item.hargaCoret
            holder.tvHargaCoret.paintFlags = holder.tvHargaCoret.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.tvHargaCoret.visibility = View.GONE
        }

        // Cek Rating
        if (item.rating != null) {
            holder.tvRating.visibility = View.VISIBLE
            holder.tvRating.text = "★ ${item.rating}"
        } else {
            holder.tvRating.visibility = View.GONE
        }

        // Cek Terjual
        if (item.terjual != null) {
            holder.tvTerjual.visibility = View.VISIBLE
            holder.tvTerjual.text = "${item.terjual} terjual"
        } else {
            holder.tvTerjual.visibility = View.GONE
        }

        // Klik Tombol Beli
        holder.btnBeli.setOnClickListener { onAddClick(item) }
        holder.itemView.setOnClickListener { onAddClick(item) }
    }

    override fun getItemCount() = list.size
}