package com.example.todolist.data

interface TodoRepository {
    fun getTodos(): List<Todo>
    fun addTodo(title: String)
    fun deleteTodo(id: Int)
    fun updateTodo(id: Int, newTitle: String)
    fun getTodoById(id: Int): Todo?
}