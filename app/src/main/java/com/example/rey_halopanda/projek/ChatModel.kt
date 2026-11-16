package com.example.rey_halopanda.projek

// Model Daftar Chat
data class ChatModel(
    val nama: String, val label: String, val pesan: String,
    val waktu: String, val badge: String
)

// Model Isi Chat Room
data class ChatMessage(
    val text: String,
    val isOutgoing: Boolean,
    val type: Int
) {
    companion object {
        const val TYPE_TEXT = 1
        const val TYPE_PRODUCT = 2
        const val TYPE_INVOICE = 3
    }
}