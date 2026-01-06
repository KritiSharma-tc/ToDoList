package com.example.todolist

import java.time.Instant
import java.util.Date

/**
 * Implementation of TodoRepository that manages todo items in memory.
 * This follows the Repository pattern for proper MVVM architecture.
 */
class TodoRepositoryImpl : TodoRepository {

    private val todoList = mutableListOf<Todo>()

    override fun getAllTodos(): List<Todo> {
        return todoList.toList()
    }

    override fun addTodo(title: String) {
        todoList.add(Todo(System.currentTimeMillis(), title, Date.from(Instant.now())))
    }

    override fun deleteTodo(id: Long) {
        todoList.removeIf {
            it.id == id
        }
    }
}