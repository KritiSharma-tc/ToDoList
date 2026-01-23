import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDatabase
import com.example.todolist.data.TodoRepository
import com.example.todolist.data.TodoRepositoryImpl

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository

    private val _todos = MutableLiveData<List<Todo>>(emptyList())
    val todos: LiveData<List<Todo>> = _todos

    init {
        val db = TodoDatabase.getInstance(application)
        repository = TodoRepositoryImpl(db.todoDao())
        loadTodos()
    }

    private fun loadTodos() {
        _todos.value = repository.getTodos()
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

    fun getTodoById(id: Int): Todo? =
        repository.getTodoById(id)
}
