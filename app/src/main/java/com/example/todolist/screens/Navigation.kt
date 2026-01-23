package com.example.todolist.screens


import TodoListPage
import TodoViewModel
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.todolist.ui.EditTodoScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val todoViewModel: TodoViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(
            LocalContext.current.applicationContext as Application
        )
    )

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
