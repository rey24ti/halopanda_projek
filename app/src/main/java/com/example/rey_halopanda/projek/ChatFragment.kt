package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class ChatFragment : Fragment() {

    private lateinit var rvChatList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chat, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvChatList = view.findViewById(R.id.rvChatList)


        val etSearch = view.findViewById<EditText>(R.id.etSearchChat)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(requireContext(), "Mencari: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            false
        }


        view.findViewById<View>(R.id.btnTanyaB2B).setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Konsultasi B2B...", Toast.LENGTH_SHORT).show()
        }

        setupListChat()
    }

    private fun setupListChat() {

        val dataChat = listOf(
            ChatModel(
                nama = "TB Jaya Mandiri",
                label = "Pusat",
                pesan = "Siap Pak, armada dump truk siap diberangkatkan",
                waktu = "10:21",
                badge = "2"
            ),
            ChatModel(
                nama = "Mitra Bangunan Sukma",
                label = "Supplier",
                pesan = "Kabel Roling BRC D10 Full sudah laku ya",
                waktu = "09:15",
                badge = ""
            ),
            ChatModel(
                nama = "Depo Semen Gresik & Holcim",
                label = "Supplier",
                pesan = "Stok semen PCC 50kg ready 800 sak",
                waktu = "Kemarin",
                badge = ""
            ),
            ChatModel(
                nama = "Baja Ringan & Spandek Clagon",
                label = "Supplier",
                pesan = "Untuk harga PO 2025/2026 ada di note",
                waktu = "Kemarin",
                badge = ""
            ),
            ChatModel(
                nama = "CS Logistik Proyek",
                label = "Depatcher",
                pesan = "Siap Pak, Jalan sudah bisa dilewati",
                waktu = "2 hari",
                badge = ""
            )
        )

        rvChatList.layoutManager = LinearLayoutManager(requireContext())

        rvChatList.adapter = object : RecyclerView.Adapter<ChatVH>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatVH {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat, parent, false)
                return ChatVH(v)
            }

            override fun onBindViewHolder(holder: ChatVH, position: Int) {
                holder.bind(dataChat[position])
            }

            override fun getItemCount() = dataChat.size
        }
    }

    data class ChatModel(
        val nama: String, val label: String, val pesan: String,
        val waktu: String, val badge: String
    )

    inner class ChatVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNama: TextView = itemView.findViewById(R.id.tvNamaChat)
        private val tvLabel: TextView = itemView.findViewById(R.id.tvLabelPusat)
        private val tvPesan: TextView = itemView.findViewById(R.id.tvPesanTerakhir)
        private val tvWaktu: TextView = itemView.findViewById(R.id.tvWaktuChat)
        private val tvBadge: TextView = itemView.findViewById(R.id.tvBadgeChat)
        private val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatarChat)

        fun bind(data: ChatModel) {
            tvNama.text = data.nama
            tvLabel.text = data.label
            tvPesan.text = data.pesan
            tvWaktu.text = data.waktu

            if (data.badge.isEmpty()) {
                tvBadge.visibility = View.GONE
            } else {
                tvBadge.visibility = View.VISIBLE
                tvBadge.text = data.badge
            }
            itemView.setOnClickListener {
                val fragment = ChatRoomFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null) // Agar bisa kembali ke halaman Chat
                    .commit()
            }
        }
    }
}