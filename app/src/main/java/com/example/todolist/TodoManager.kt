package com.example.todolist

import java.time.Instant
import java.util.Date

object TodoManager {

    private val todoList = mutableListOf<Todo>()


    fun getAllTodo() : List<Todo>{
        return todoList
    }

    fun addTodo(title : String){
        todoList.add(Todo(System.currentTimeMillis().toInt(),title, Date.from(Instant.now())))
    }

    fun deleteTodo(id : Int){
        todoList.removeIf{
            it.id==id
        }
    }

    fun updateTodo(id: Int, newTitle: String) {
        val index = todoList.indexOfFirst { it.id == id }
        if (index != -1) {
            val oldTodo = todoList[index]
            todoList[index] = oldTodo.copy(
                title = newTitle,
                createdAt = Date.from(Instant.now()) // ⏰ update time
            )
        }
    }

}

