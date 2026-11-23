package com.example.rey_halopanda.projek.data.model

data class TaskModel(
    val id: Long? = null,
    val task_name: String,
    val deadline: String,
    val is_done: Boolean = false
)