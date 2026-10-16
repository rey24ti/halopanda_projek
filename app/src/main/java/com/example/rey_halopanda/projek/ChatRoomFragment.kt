package com.example.rey_halopanda.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class ChatRoomFragment : Fragment() {

    private lateinit var rvChatRoom: RecyclerView
    private lateinit var adapter: ChatRoomAdapter
    private val messageList = mutableListOf<ChatMessage>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chat_room, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvChatRoom = view.findViewById(R.id.rvChatRoom)
        setupDummyData()


        adapter = ChatRoomAdapter(messageList)
        rvChatRoom.layoutManager = LinearLayoutManager(requireContext())
        rvChatRoom.adapter = adapter


        view.findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            // Kembali ke ChatFragment
            parentFragmentManager.popBackStack()
        }


        val etMessage = view.findViewById<EditText>(R.id.etMessage)
        view.findViewById<ImageView>(R.id.btnSend).setOnClickListener {
            val text = etMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                messageList.add(ChatMessage(text, true, ChatMessage.TYPE_TEXT))
                adapter.notifyItemInserted(messageList.size - 1)
                rvChatRoom.scrollToPosition(messageList.size - 1)
                etMessage.text.clear()
            } else {
                Toast.makeText(requireContext(), "Tulis pesan dulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupDummyData() {
        messageList.add(ChatMessage("Halo Pak Mandor, Truk sudah kosong. Ada yang bisa kami urus untuk pengadaan material proyek hari ini?", false, ChatMessage.TYPE_TEXT))
        messageList.add(ChatMessage("Pag Admin, mau tanya ketersediaan semen 50kg untuk 50 sak. Bisa diantar ke lokasi proyek?", true, ChatMessage.TYPE_TEXT))
        messageList.add(ChatMessage("Stok ready banyak Pak. Ini kami lampirkan produk dari estimasi harga grosir proyek:", false, ChatMessage.TYPE_TEXT))
        messageList.add(ChatMessage("", false, ChatMessage.TYPE_PRODUCT)) // Kartu Produk
        messageList.add(ChatMessage("", false, ChatMessage.TYPE_INVOICE)) // Kartu Invoice
        messageList.add(ChatMessage("Baik Pak, pesanan sudah kami proses ya.", false, ChatMessage.TYPE_TEXT))
    }


    inner class ChatRoomAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int): Int = messages[position].type

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return when (viewType) {
                ChatMessage.TYPE_TEXT -> {
                    val view = inflater.inflate(R.layout.item_chat_left, parent, false)
                    TextViewHolder(view)
                }
                ChatMessage.TYPE_PRODUCT -> {
                    val view = inflater.inflate(R.layout.item_chat_product, parent, false)
                    ProductViewHolder(view)
                }
                ChatMessage.TYPE_INVOICE -> {
                    val view = inflater.inflate(R.layout.item_chat_invoice, parent, false)
                    InvoiceViewHolder(view)
                }
                else -> throw IllegalArgumentException("Unknown type")
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val message = messages[position]
            when (holder) {
                is TextViewHolder -> {
                    if (message.isOutgoing) {
                    }
                    holder.bind(message.text)
                }
                is ProductViewHolder -> {}
                is InvoiceViewHolder -> {}
            }
        }

        override fun getItemCount() = messages.size
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {

        }
    }
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    data class ChatMessage(
        val text: String,
        val isOutgoing: Boolean,
        val type: Int
    ) {
        companion object {
            const val TYPE_TEXT = 0
            const val TYPE_PRODUCT = 1
            const val TYPE_INVOICE = 2
        }
    }
}