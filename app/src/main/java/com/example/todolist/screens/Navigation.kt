package com.example.todolist.screens


import TodoListPage
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.todolist.ui.EditTodoScreen
import com.example.todolist.viewModel.TodoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val todoViewModel: TodoViewModel = viewModel()

    NavHost(navController, startDestination = "list") {

        composable("list") {
            TodoListPage(todoViewModel, navController)
        }

        composable(
            "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            EditTodoScreen(id, todoViewModel, navController)
        }
    }
}
