package com.example.todolist.data

import androidx.room.*
import java.util.Date

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY createdAt DESC")
    fun getTodos(): List<Todo>

    @Insert
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("UPDATE todo SET title = :title, createdAt = :date WHERE id = :id")
    fun update(id: Int, title: String, date: Date)

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id: Int): Todo?
}