package com.example.todolist

import java.time.Instant
import java.util.Date

/**
 * Model - Todo data class
 * Represents a todo item entity in the MVVM architecture.
 * Contains only data, no business logic.
 */
data class Todo(
    var id: Int,
    var title: String,
    var createdAt: Date

)
