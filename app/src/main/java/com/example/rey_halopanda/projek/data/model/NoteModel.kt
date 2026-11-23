package com.example.rey_halopanda.projek.data.model

data class NoteModel(
    val id: Long? = null,
    val title: String,
    val content: String,
    val status: String = "To Do"
)