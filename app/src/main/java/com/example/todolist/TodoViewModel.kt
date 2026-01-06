package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for managing Todo list UI state.
 * Follows strict MVVM: depends on Repository abstraction, not concrete implementation.
 * The repository is injected via constructor for better testability and decoupling.
 */
class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoList

    init {
        loadTodos()
    }

    private fun loadTodos() {
        _todoList.value = repository.getAllTodos().reversed()
    }

    fun addTodo(title: String) {
        repository.addTodo(title)
        loadTodos()
    }

    fun deleteTodo(id: Int) {
        repository.deleteTodo(id)
        loadTodos()
    }
}