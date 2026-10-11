package com.example.rey_halopanda.projek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class KategoriKatalogAdapter(
    private val listKatalog: List<KategoriKatalogModel>,
    private val onClick: (KategoriKatalogModel) -> Unit
) : RecyclerView.Adapter<KategoriKatalogAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvSubtitle: TextView = view.findViewById(R.id.tvSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_katalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listKatalog[position]
        holder.ivIcon.setImageResource(item.iconResId)
        holder.tvTitle.text = item.title
        holder.tvSubtitle.text = item.subtitle
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = listKatalog.size
}