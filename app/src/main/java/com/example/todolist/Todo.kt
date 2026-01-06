package com.example.todolist

import java.util.Date

/**
 * Model - Todo data class
 * Represents a todo item entity in the MVVM architecture.
 * Contains only data, no business logic.
 */
data class Todo(
    val id: Long,
    var title: String,
    val createdAt: Date
)
