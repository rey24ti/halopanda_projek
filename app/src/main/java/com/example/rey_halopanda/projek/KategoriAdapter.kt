package com.example.rey_halopanda.projek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class KategoriAdapter(
    private val listKategori: List<KategoriModel>,
    private val onClick: (KategoriModel) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivKategori: ImageView = view.findViewById(R.id.ivKategori)
        val tvNama: TextView = view.findViewById(R.id.tvNamaKategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kategori, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listKategori[position]
        holder.ivKategori.setImageResource(item.iconResId)
        holder.tvNama.text = item.nama
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = listKategori.size
}