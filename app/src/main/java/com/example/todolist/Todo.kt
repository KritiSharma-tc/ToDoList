package com.example.todolist

import java.time.Instant
import java.util.Date

data class Todo(
    val id: Int,
    val title: String,
    val createdAt: Date
)
