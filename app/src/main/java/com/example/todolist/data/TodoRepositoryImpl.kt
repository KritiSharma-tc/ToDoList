package com.example.todolist.data


import android.os.Build

import java.time.Instant
import java.util.Date

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override fun getTodos(): List<Todo> = dao.getTodos()

    override fun addTodo(title: String) {
        dao.insert(
            Todo(
                title = title,
                createdAt = Date.from(Instant.now())
            )
        )
    }

    override fun deleteTodo(id: Int) {
        dao.getTodoById(id)?.let { dao.delete(it) }
    }

    override fun updateTodo(id: Int, newTitle: String) {
        dao.update(
            id = id,
            title = newTitle,
            date = Date.from(Instant.now())
        )
    }

    override fun getTodoById(id: Int): Todo? =
        dao.getTodoById(id)
}