package com.example.rey_halopanda.projek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R
import com.google.android.material.button.MaterialButton

class ProdukAdapter(
    private val listProduk: List<ProdukModel>,
    private val onBeliClick: (ProdukModel) -> Unit
) : RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduk: ImageView = view.findViewById(R.id.ivProduk)
        val tvNama: TextView = view.findViewById(R.id.tvNamaProduk)
        val tvHarga: TextView = view.findViewById(R.id.tvHarga)
        val tvHargaCoret: TextView = view.findViewById(R.id.tvHargaCoret)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val tvTerjual: TextView = view.findViewById(R.id.tvTerjual)
        val tvFlashTag: TextView = view.findViewById(R.id.tvFlashSaleTag)
        val btnBeli: MaterialButton = view.findViewById(R.id.btnBeli)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listProduk[position]
        holder.ivProduk.setImageResource(item.gambarResId)
        holder.tvNama.text = item.nama
        holder.tvHarga.text = item.harga
        holder.tvRating.text = "★ ${item.rating}"
        holder.tvTerjual.text = "${item.terjual} terjual"


        if (item.isFlashSale) {
            holder.tvFlashTag.visibility = View.VISIBLE
            holder.tvHargaCoret.visibility = View.VISIBLE
            holder.tvHargaCoret.text = item.hargaCoret
        } else {
            holder.tvFlashTag.visibility = View.GONE
            holder.tvHargaCoret.visibility = View.GONE
        }

        holder.btnBeli.setOnClickListener { onBeliClick(item) }
    }

    override fun getItemCount(): Int = listProduk.size
}