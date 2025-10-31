package br.com.julianosgarbossa.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import br.com.julianosgarbossa.base.Routes
import br.com.julianosgarbossa.model.Book

@Composable
fun ListBookScreen(
    onNavigate: (String) -> Unit,
    viewModel: ListBookViewModel = viewModel()
) {
    val books by viewModel.filteredBooks.collectAsState()
    val query by viewModel.query.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refresh()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.setQuery(it) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Buscar por título ou autor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        if (books.isEmpty()) {
            BoxEmptyState(modifier = Modifier.weight(1f))
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(books) { book ->
                    BookItem(
                        book = book,
                        onEdit = { onNavigate(Routes.EditBook.buildRoute(book.id)) },
                        onDelete = { viewModel.deleteBook(book.id) },
                        onOpen = { onNavigate(Routes.DetailBook.buildRoute(book.id)) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            FloatingActionButton(onClick = { onNavigate(Routes.CreateBook.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Livro")
            }
        }
    }
}

@Composable
private fun BoxEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Nenhum livro cadastrado", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun BookItem(
    book: Book,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onOpen: () -> Unit
) {
    var showConfirm by remember { mutableStateOf(false) }

    if (showConfirm) {
        AlertDialog(
            onDismissRequest = { showConfirm = false },
            title = { Text("Deletar livro") },
            text = { Text("Tem certeza que deseja deletar este livro?") },
            confirmButton = {
                TextButton(onClick = { showConfirm = false; onDelete() }) { Text("Deletar") }
            },
            dismissButton = { TextButton(onClick = { showConfirm = false }) { Text("Cancelar") } }
        )
    }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onOpen
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(text = book.title.ifBlank { "(Sem título)" }, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(text = book.author.ifBlank { "(Autor desconhecido)" }, style = MaterialTheme.typography.bodyMedium)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = androidx.compose.ui.graphics.Color(0xFFFFC107))
                    Spacer(Modifier.width(4.dp))
                    Text(text = book.rating.toString())
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Status: ${book.status}", style = MaterialTheme.typography.bodyMedium)
                Row {
                    IconButton(onClick = onEdit) { Icon(Icons.Default.Edit, contentDescription = "Editar") }
                    IconButton(onClick = { showConfirm = true }) { Icon(Icons.Default.Delete, contentDescription = "Deletar") }
                }
            }
        }
    }
}


