package com.example.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Locale




@Composable
fun TodoListPage(viewModel: TodoViewModel, navController: NavController) {

    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    val isValidInput = inputText.isNotEmpty()


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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                modifier= Modifier.weight(1f),
                value = inputText,
                onValueChange = {
                    inputText = it

                },
            )

            Spacer(modifier = Modifier.width(12.dp))

            Button(enabled = isValidInput,
                   onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            }) {
                Text(text = "Add", color=Color.White, fontSize=18.sp)
            }
        }

        todoList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it){index: Int, item: Todo ->
                        TodoItem(item = item, onDelete = {
                            viewModel.deleteTodo(item.id)
                        }, {
                            navController.navigate("editTodoItem/${item.id}")
                        } )
                    }
                }
            )
        }
    }

}

@Composable
fun TodoItem(item : Todo,onDelete : ()-> Unit, onEdit : ()-> Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.White
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }

            Spacer(modifier = Modifier.width(16.dp))

    IconButton(onClick = onEdit) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit",
            tint = Color.White
        )
    }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }


}}
