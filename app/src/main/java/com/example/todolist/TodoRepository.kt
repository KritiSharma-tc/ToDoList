package com.example.todolist

/**
 * Repository interface for Todo data operations.
 * This abstraction ensures the ViewModel doesn't depend on concrete implementations.
 */
interface TodoRepository {
    fun getAllTodos(): List<Todo>
    fun addTodo(title: String)
    fun deleteTodo(id: Int)
}
