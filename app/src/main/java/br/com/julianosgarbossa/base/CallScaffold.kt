package br.com.julianosgarbossa.base

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.julianosgarbossa.ui.theme.MyLibraryTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallScaffold() {
    MyLibraryTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val route = backStackEntry?.destination?.route ?: Routes.ListBooks.route
                val title = when {
                    route.startsWith(Routes.CreateBook.route) -> "Cadastrando Livro"
                    route.startsWith("${Routes.EditBook.route.substringBefore("/{")}") -> "Editando Livro"
                    route.startsWith("${Routes.DetailBook.route.substringBefore("/{")}") -> "Detalhes do Livro"
                    else -> "Minha Biblioteca"
                }
                CenterAlignedTopAppBar(
                    title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            Navigation(modifier = Modifier, contentPadding = innerPadding, navController = navController)
        }
    }
}


