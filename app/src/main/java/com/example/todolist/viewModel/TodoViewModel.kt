package com.example.todolist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoRepository
import com.example.todolist.data.TodoRepositoryImpl

class TodoViewModel(
    private val repository: TodoRepository = TodoRepositoryImpl()
) : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>(emptyList())
    val todos: LiveData<List<Todo>> = _todos

    init {
        loadTodos()
    }

    private fun loadTodos() {
        _todos.value = repository.getTodos().reversed()
    }

    fun addTodo(title: String) {
        if (title.isBlank()) return
        repository.addTodo(title)
        loadTodos()
    }

    fun deleteTodo(id: Int) {
        repository.deleteTodo(id)
        loadTodos()
    }

    fun updateTodo(id: Int, title: String) {
        repository.updateTodo(id, title)
        loadTodos()
    }

    fun getTodoById(id: Int): Todo? {
        return repository.getTodoById(id)
    }
}
