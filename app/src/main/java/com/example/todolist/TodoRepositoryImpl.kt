package com.example.todolist

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
        val timestamp = System.currentTimeMillis()
        todoList.add(Todo(timestamp, title, Date(timestamp)))
    }

    override fun deleteTodo(id: Long) {
        todoList.removeIf {
            it.id == id
        }
    }
}