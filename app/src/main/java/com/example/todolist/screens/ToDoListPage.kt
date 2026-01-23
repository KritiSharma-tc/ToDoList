

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.data.Todo
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoListPage(
    viewModel: TodoViewModel,
    navController: NavController
) {
    val todos by viewModel.todos.observeAsState(emptyList())
    var input by remember { mutableStateOf("") }

    Column( modifier = Modifier .fillMaxHeight() .padding(top=60.dp) )
    {
        Text(text= "To-Do List",
             fontSize = 40.sp, fontWeight = Bold,
             modifier = Modifier.fillMaxWidth(),
             textAlign = TextAlign.Center)

        Text(text= "Manage your tasks efficiently",
             fontSize = 25.sp,
             modifier = Modifier.fillMaxWidth(),
             textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    viewModel.addTodo(input)
                    input = ""
                },
                enabled = input.isNotBlank()
            ) {
                Text(text = "Add", color=Color.White, fontSize=18.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onDelete = { viewModel.deleteTodo(todo.id) },
                    onEdit = {
                        navController.navigate("edit/${todo.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Card(modifier = Modifier.padding(vertical = 6.dp)) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(todo.title, fontSize = 18.sp)
                Text(
                    SimpleDateFormat("dd MMM, HH:mm", Locale.ENGLISH)
                        .format(todo.createdAt),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            IconButton(onClick = onDelete) {
                Text("🗑")
            }
        }
    }
}
