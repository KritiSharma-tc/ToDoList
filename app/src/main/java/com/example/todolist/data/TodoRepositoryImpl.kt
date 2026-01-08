package com.example.todolist.data


import android.os.Build
import java.time.Instant
import java.util.Date

class TodoRepositoryImpl : TodoRepository {

    private val todos = mutableListOf<Todo>()

    override fun getTodos(): List<Todo> = todos

    override fun addTodo(title: String) {
        todos.add(
            Todo(
                id = System.currentTimeMillis().toInt(),
                title = title,
                createdAt = Date.from(Instant.now())
            )
        )
    }

    override fun deleteTodo(id: Int) {
        todos.removeIf { it.id == id }
    }

    override fun updateTodo(id: Int, newTitle: String) {
        val index = todos.indexOfFirst { it.id == id }
        if (index != -1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                todos[index] = todos[index].copy(
                    title = newTitle,
                    createdAt = Date.from(Instant.now())
                )
            }
        }
    }

    override fun getTodoById(id: Int): Todo? {
        return todos.find { it.id == id }
    }
}
