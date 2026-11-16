package com.example.rey_halopanda.projek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KategoriAdapter(
    private val list: List<KategoriModel>,
    private val onClick: (KategoriModel) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvNama: TextView = v.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        (holder.itemView as TextView).text = item.nama
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = list.size
}