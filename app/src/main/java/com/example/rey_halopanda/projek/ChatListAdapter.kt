package com.example.rey_halopanda.projek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class ChatListAdapter(
    private val listContact: List<ChatModel>,
    private val onClick: (ChatModel) -> Unit
) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ID ini diambil dari kode ChatFragment Anda sebelumnya
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaChat)
        val tvLabel: TextView = itemView.findViewById(R.id.tvLabelPusat)
        val tvPesan: TextView = itemView.findViewById(R.id.tvPesanTerakhir)
        val tvWaktu: TextView = itemView.findViewById(R.id.tvWaktuChat)
        val tvBadge: TextView = itemView.findViewById(R.id.tvBadgeChat)
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatarChat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listContact[position]
        with(holder) {
            tvNama.text = item.nama
            tvLabel.text = item.label
            tvPesan.text = item.pesan
            tvWaktu.text = item.waktu

            if (item.badge.isEmpty()) {
                tvBadge.visibility = View.GONE
            } else {
                tvBadge.visibility = View.VISIBLE
                tvBadge.text = item.badge
            }

            itemView.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount(): Int = listContact.size
}