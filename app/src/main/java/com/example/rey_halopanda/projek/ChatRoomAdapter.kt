package com.example.rey_halopanda.projek

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rey_halopanda.R

class ChatRoomAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return messages[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ChatMessage.TYPE_TEXT -> {
                val view = inflater.inflate(R.layout.item_chat_bubble, parent, false)
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
            is TextViewHolder -> holder.bind(message)
            is ProductViewHolder -> { /* Logika produk */ }
            is InvoiceViewHolder -> { /* Logika invoice */ }
        }
    }

    override fun getItemCount() = messages.size

    // 1 ViewHolder untuk Kiri dan Kanan
    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutBubble: LinearLayout = itemView.findViewById(R.id.layoutBubble)
        private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        private val tvTime: TextView = itemView.findViewById(R.id.tvTime)

        fun bind(message: ChatMessage) {
            tvMessage.text = message.text
            tvTime.text = "10:00" // Anda bisa mengganti dengan waktu asli

            if (message.isOutgoing) {
                // Jika pesan kita (Kanan)
                layoutBubble.gravity = Gravity.END
                tvMessage.setBackgroundColor(Color.parseColor("#0D47A1"))
                tvMessage.setTextColor(Color.WHITE)
            } else {
                // Jika pesan orang lain (Kiri)
                layoutBubble.gravity = Gravity.START
                tvMessage.setBackgroundColor(Color.parseColor("#FFFFFF"))
                tvMessage.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}