package br.com.julianosgarbossa.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.julianosgarbossa.screens.CreateBookScreen
import br.com.julianosgarbossa.screens.DetailBookScreen
import br.com.julianosgarbossa.screens.EditBookScreen
import br.com.julianosgarbossa.screens.ListBookScreen

@Composable
fun Navigation(modifier: Modifier = Modifier, contentPadding: PaddingValues, navController: androidx.navigation.NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.ListBooks.route,
        modifier = modifier.padding(contentPadding)
    ) {
        composable(Routes.ListBooks.route) {
            ListBookScreen(onNavigate = { navController.navigate(it) })
        }
        composable(Routes.CreateBook.route) {
            CreateBookScreen(onDone = { navController.popBackStack() })
        }
        composable(
            route = Routes.EditBook.route,
            arguments = listOf(navArgument(Routes.EditBook.ARG_BOOK_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(Routes.EditBook.ARG_BOOK_ID) ?: return@composable
            EditBookScreen(bookId = id, onDone = { navController.popBackStack() })
        }
        composable(
            route = Routes.DetailBook.route,
            arguments = listOf(navArgument(Routes.DetailBook.ARG_BOOK_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(Routes.DetailBook.ARG_BOOK_ID) ?: return@composable
            DetailBookScreen(bookId = id)
        }
    }
}


